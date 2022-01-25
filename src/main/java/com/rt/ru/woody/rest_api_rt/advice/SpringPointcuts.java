package com.rt.ru.woody.rest_api_rt.advice;

import org.aspectj.lang.annotation.Pointcut;

public class SpringPointcuts {

        @Pointcut(value = "within(@org.springframework.stereotype.Repository *)" +
                " || within(@org.springframework.stereotype.Service *)" +
                " || within(@org.springframework.web.bind.annotation.RestController *)")
        public void springBeanPointcut() {}

        @Pointcut(value = "within(com.rt.ru.woody.rest_api_rt.repository.*)" +
                " || within(com.rt.ru.woody.rest_api_rt.service..*)" +
                " || within(com.rt.ru.woody.rest_api_rt.controller..*)")
        public void applicationPackagePointcut() {

        }

        @Pointcut("within(com.rt.ru.woody.rest_api_rt.service..*)")
        public void serviceBeanPointcut() {
        }
}
