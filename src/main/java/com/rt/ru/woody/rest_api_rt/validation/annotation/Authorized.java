package com.rt.ru.woody.rest_api_rt.validation.annotation;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Documented
public @interface Authorized {

    String code() default "";
}
