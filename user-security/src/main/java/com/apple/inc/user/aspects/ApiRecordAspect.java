package com.apple.inc.user.aspects;

import com.apple.inc.user.annotations.ApiRecord;
import com.apple.inc.user.entities.mongo.ApiRecordEntity;
import com.apple.inc.user.handler.ApiRecordHandler;
import com.apple.inc.user.util.threadLocal.ThreadContextDirectory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.concurrent.Future;

@Slf4j
@Order(1)
@Aspect
@Component
@RequiredArgsConstructor
public class ApiRecordAspect {

    private final ApiRecordHandler apiRecordHandler;

    @Around("@annotation(apiRecord)")
    public Object apiRecord(ProceedingJoinPoint proceedingJoinPoint, ApiRecord apiRecord) throws Throwable {
        Future<ApiRecordEntity> apiRecordEntityFuture = apiRecordHandler.persistRequest(proceedingJoinPoint, ThreadContextDirectory.get(), apiRecord.persistRequest());
        log.info("apiRecordEntityFuture {}", apiRecordEntityFuture);
        Object result = proceedingJoinPoint.proceed();
        apiRecordHandler.persistResponse(proceedingJoinPoint, result, apiRecordEntityFuture);
        return result;
    }

}
