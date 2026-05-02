package com.apple.inc.user.util;

import com.apple.inc.user.exceptions.POBaseException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;

@Slf4j
public class CallableTaskExecutor extends AbstractTaskExecutor implements AsyncTaskExecutor {

    private final ThreadPoolTaskExecutor DEFAULT_EXECUTOR;

    private ListableBeanFactory beanFactory;

    public CallableTaskExecutor() {
        this.DEFAULT_EXECUTOR = new ThreadPoolTaskExecutor();
    }

    public CallableTaskExecutor(ThreadPoolTaskExecutor defaultExecutor) {
        this.DEFAULT_EXECUTOR = defaultExecutor;
    }

    @Override
    public <T> void execute(Supplier<T> supplier) {
        queue(supplier);
    }

    @Override
    public <T> void execute(Supplier<T> supplier, String poolId) {
        queue(supplier, poolId);
    }

    @Override
    public <T> Future<T> queue(Supplier<T> supplier) {
        return queue(supplier, null);
    }

    @Override
    public <T> Future<T> queue(Supplier<T> supplier, String poolId) {
        return resolveApplicableExecutor(poolId, beanFactory, DEFAULT_EXECUTOR).submit(new ContextAwareCallable<T>(supplier));
    }

    @Override
    public <T> Optional<T> get(Future<T> future, int timeOutInMillis) {
        return get(future, timeOutInMillis, true);
    }

    @Override
    public <T> Optional<T> get(Future<T> future, int timeOutInMillis, boolean isFailureSuppressed) {
        try {
            log.debug("Is future null ? : {}", Objects.isNull(future));
            return Optional.ofNullable(future.get(timeOutInMillis, TimeUnit.MILLISECONDS));

        } catch (Exception e) {
            if (isFailureSuppressed) {
                log.error(e.getClass().getSimpleName() + " exception has occurred", e);
                return Optional.empty();
            }

            if (e.getCause() instanceof POBaseException) {
                log.info(
                        "Cause of exception "
                                + e.getClass().getSimpleName()
                                + " is of type POBaseException. Hence return the cause.");
                throw (POBaseException) e.getCause();
            }
            log.error("Exception in async executor", e);

            throw new POBaseException("Exception while getting result from Future<>.", e);
        }
    }

    protected ThreadPoolTaskExecutor resolveApplicableExecutor(
            String poolId,
            ListableBeanFactory beanFactory,
            ThreadPoolTaskExecutor DEFAULT_EXECUTOR) {

        return StringUtils.isEmpty(poolId) || Objects.isNull(beanFactory)
                ? DEFAULT_EXECUTOR
                : beanFactory.getBean(poolId, ThreadPoolTaskExecutor.class);
    }
}
