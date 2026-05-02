package com.apple.inc.user.handler;

import com.apple.inc.user.constants.BeanConstants;
import com.apple.inc.user.dto.Response;
import com.apple.inc.user.dto.FieldParam;
import com.apple.inc.user.util.AsyncTaskExecutor;
import com.apple.inc.user.util.mapper.CustomObjectMapper;
import com.apple.inc.user.util.threadLocal.ThreadContext;
import com.apple.inc.user.entities.mongo.ApiRecordEntity;
import com.apple.inc.user.services.IApiRecordEntityDao;
import com.apple.inc.user.util.reflection.ReflectionUtils;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;

import java.lang.annotation.Annotation;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import static com.apple.inc.user.util.mapper.CustomObjectMapper._toString;
import static org.springframework.data.util.CustomCollections.isCollection;

@Slf4j
@Component
@RequiredArgsConstructor
public class ApiRecordHandler {

    private final IApiRecordEntityDao apiRecordEntityDao;
    private final AsyncTaskExecutor asyncTaskExecutor;

    private final String environment = Optional.ofNullable(System.getenv("ENVIRONMENT"))
            .orElse(null);

    @Async(BeanConstants.API_RECORD_POOL)
    public Future<ApiRecordEntity> persistRequest(ProceedingJoinPoint pjp, ThreadContext threadContext, boolean persistRequest) {
        String[] methodName = pjp.getStaticPart().toShortString().split("\\(");
        return createAndPersistRequest(pjp, methodName[1], threadContext.getCommonRequestIdentifier(), persistRequest);
    }

    @Async(BeanConstants.API_RECORD_POOL)
    private CompletableFuture<ApiRecordEntity> createAndPersistRequest(Object request, String requestType, String crid, boolean persistRequest) {
        ApiRecordEntity bookEntity = null;
        try {
            if (persistRequest) {
                log.debug("Persisting request in database");
                bookEntity = apiRecordEntityDao.save(createApiRecord(request, requestType, crid));
                return CompletableFuture.completedFuture(bookEntity);
            }
            log.debug("Not persisting Request in database");
            Optional<ApiRecordEntity> apiRecordEntity = apiRecordEntityDao.find(crid);
            if (apiRecordEntity.isPresent()) return CompletableFuture.completedFuture(apiRecordEntity.get());
        } catch (Exception e) {
            log.error("Exception while persisting request");
        }
        return CompletableFuture.completedFuture(bookEntity);
    }

    private ApiRecordEntity createApiRecord(Object request,
                                            String requestType,
                                            String crid) {
        ApiRecordEntity entity = new ApiRecordEntity();
        Object requestObj = getRequestObject(request);
        if (!isCollection((Class<?>) requestObj)) {
            try {
                List<FieldParam> fieldParams = ReflectionUtils.getAllFields(requestObj);
                Map<Class<? extends Annotation>, String> annotationStringMap = ReflectionUtils.annotatedMap(fieldParams);
            } catch (IllegalAccessException e) {
                log.error("Exception while handling request ", e);
            }
        }
        entity.setRequest(_toString(requestObj));
        entity.setRequestId(crid);
        entity.setRequestApi(requestType);
        entity.setEnvironment(environment);
        return entity;
    }

    @Async(BeanConstants.API_RECORD_POOL)
    public void persistResponse(ProceedingJoinPoint proceedingJoinPoint,  Object response, Future<ApiRecordEntity> future) throws ExecutionException, InterruptedException {
        int httpStatus = 0;
        for (Object arg: proceedingJoinPoint.getArgs()) {
            if (arg instanceof HttpServletResponse) {
                httpStatus = ((HttpServletResponse) arg).getStatus();
            }
        }
        persistResponse(response, httpStatus, future);
    }


    private void persistResponse(Object response,
                                 int httpStatus,
                                 Future<ApiRecordEntity> future) throws ExecutionException, InterruptedException {
        ApiRecordEntity bookEntity;
        Optional<ApiRecordEntity> apiRecordEntity = asyncTaskExecutor.get(future, 3000, true);
        if (apiRecordEntity.isPresent()) {
            bookEntity = future.get();
            handleResponse(response, bookEntity);
            bookEntity.setHttpStatusCode(String.valueOf(httpStatus));
            apiRecordEntityDao.save(bookEntity);
            log.debug("Response persisted in database");
        }
    }

    private Object getRequestObject(Object request) {
        if (request instanceof ProceedingJoinPoint pjp) {
            if (pjp.getArgs().length > 0) {
                return pjp.getArgs()[0];
            }
        }
        return request;
    }

    private void handleResponse(Object response,
                                ApiRecordEntity bookEntity) {

        if (response instanceof Response<?>) {
            bookEntity.setResponse(CustomObjectMapper._toString(response));
            bookEntity.setHttpStatusCode(((Response<?>) response).getStatusCode());
        } else {
            ModelAndView modelAndView = (ModelAndView) response;
            Map<String, Object> objectMap = modelAndView.getModel();
            bookEntity.setResponse(CustomObjectMapper._toString(objectMap));
            bookEntity.setHttpStatusCode(getValue(objectMap, "statusCode")); //TODO
        }

        log.debug("ApiRecordEntity after handling response code {} and response {}", bookEntity.getHttpStatusCode(), bookEntity.getResponse());
    }

    private String getValue(Map<String, Object> objectMap, String key) {
        if (objectMap.containsKey(key)) {
            return String.valueOf(objectMap.get(key));
        }
        for (Map.Entry<String, Object> entry : objectMap.entrySet()) {
            if (entry.getValue() instanceof Map) {
                return getValue((Map<String, Object>) entry.getValue(), key);
            }
        }
        return null;
    }

}
