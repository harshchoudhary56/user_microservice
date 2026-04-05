package com.apple.inc.user.util.concurrent;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;

import java.util.Map;
import java.util.concurrent.Callable;
import java.util.function.Supplier;

import static com.apple.inc.user.constants.MDCConstants.COMMON_REQUEST_IDENTIFIER;

@Slf4j
public class ContextAwareCallable<T> extends AbstractContextAware implements Callable<T> {

    private Supplier<T> actualTask;

    private Map<String, String> parentLoggingContext;

    public ContextAwareCallable(Supplier<T> actualTask) {
        this.actualTask = actualTask;
        this.parentLoggingContext = MDC.getCopyOfContextMap();
    }

    @Override
    public T call() throws Exception {
        Map<String, String> childLoggingContext = parentLoggingContext;

        try {
            childLoggingContext.put(COMMON_REQUEST_IDENTIFIER, getRequestIdentifier(parentLoggingContext));
            MDC.setContextMap(childLoggingContext);
            return actualTask.get();

        } catch (Exception e) {
            log.error("Exception caught in call", e);
            throw e;
        } finally {
            MDC.clear();
            MDC.setContextMap(parentLoggingContext);
        }
    }
}

