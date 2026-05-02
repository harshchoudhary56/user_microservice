package com.apple.inc.user.util.annotations;

import com.apple.inc.user.util.constants.ApiRecordType;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Documented
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ApiRecord {

    boolean persistRequest() default true;
    boolean persistResponse() default true;

    ApiRecordType type() default ApiRecordType.DEFAULT;
}
