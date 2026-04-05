package com.apple.inc.user.util.concurrent;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.Objects;

public abstract class AbstractTaskExecutor {

    /**
     * As of now making method definition prone to BeanNotFoundException while lookup in beanFactory
     * because we want the developer to notice that respective TaskExecutor bean declaration is
     * necessary.
     *
     * @param poolId
     * @return
     */
    protected ThreadPoolTaskExecutor resolveApplicableExecutor(
            String poolId,
            ListableBeanFactory beanFactory,
            ThreadPoolTaskExecutor DEFAULT_EXECUTOR) {

        return StringUtils.isEmpty(poolId) || Objects.isNull(beanFactory)
                ? DEFAULT_EXECUTOR
                : beanFactory.getBean(poolId, ThreadPoolTaskExecutor.class);
    }
}
