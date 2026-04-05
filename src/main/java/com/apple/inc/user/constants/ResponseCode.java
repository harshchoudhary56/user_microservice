package com.apple.inc.user.constants;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ResponseCode {

    SUCCESS("SPG-0000", "SUCCESS", "SUCCESS"),
    FAILED("SPG-0001", "FAILED", "FAILED");

    private final String code;
    private final String message;
    private final String userMessage;

}
