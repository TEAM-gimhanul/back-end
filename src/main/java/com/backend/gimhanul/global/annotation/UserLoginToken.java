package com.backend.gimhanul.global.annotation;

import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
public @interface UserLoginToken {
    boolean require() default true;
}
