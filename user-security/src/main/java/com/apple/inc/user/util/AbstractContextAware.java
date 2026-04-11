package com.apple.inc.user.util;

import org.springframework.util.StringUtils;

import java.util.Map;

import static com.apple.inc.user.constants.MDCConstants.COMMON_REQUEST_IDENTIFIER;

public abstract class AbstractContextAware {

    protected String getRequestIdentifier(Map<String, String> parentLoggingContext) {
        StringBuilder requestId = new StringBuilder();
        if(!StringUtils.isEmpty(parentLoggingContext.get(COMMON_REQUEST_IDENTIFIER))) {
            requestId.append(parentLoggingContext.get(COMMON_REQUEST_IDENTIFIER));
            return requestId.append("-").append(Thread.currentThread().getName()).toString();
        } else {
            return requestId.append("ASYNC-").append(Thread.currentThread().getName()).toString();
        }

    }
}
