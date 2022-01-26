package com.rt.ru.woody.rest_api_rt.validation;

import org.springframework.web.bind.annotation.RequestHeader;

import java.lang.annotation.*;

@Target({ElementType.METHOD, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RequiredHeader {

    String param() default "none";

    String value();
}
