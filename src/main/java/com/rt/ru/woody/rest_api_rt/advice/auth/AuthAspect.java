package com.rt.ru.woody.rest_api_rt.advice.auth;

import com.rt.ru.woody.rest_api_rt.exception_handling.NoAuthFoundException;
import com.rt.ru.woody.rest_api_rt.validation.annotation.Authorized;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.*;

@Aspect
@Component
@Order(1)
@Slf4j
public class AuthAspect {

    @Autowired
    private HttpServletRequest request;

    @Before(value = "@annotation(com.rt.ru.woody.rest_api_rt.validation.annotation.Authorized)")
    public void beforeCall(JoinPoint joinPoint) {

        MethodSignature signature = (MethodSignature) joinPoint.getSignature();

        Method method = signature.getMethod();

        Authorized validateHeader = method.getAnnotation(Authorized.class);

        log.info("Getting user's auth header attribute...");

        String authHeader = Optional.ofNullable(request.getHeader(HttpHeaders.AUTHORIZATION))
                .orElse("").trim();

        if(authHeader.length() == 0) {
            throw new NoAuthFoundException();
        }

        log.info("Checking the validity of the secret entry code...");

        if(!authHeader.equals(validateHeader.code())) {
            throw new NoAuthFoundException();
        }

        log.info("Code got checked successfully.");
    }
}
