package com.apple.inc.user.util.constants;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ApiRecordType {

    DEFAULT("API_RECORD");

    private final String value;

    public static ApiRecordType getDefault() {
        return DEFAULT;
    }

}
