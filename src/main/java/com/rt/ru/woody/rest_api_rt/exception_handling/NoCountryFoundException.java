package com.rt.ru.woody.rest_api_rt.exception_handling;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NoCountryFoundException extends RuntimeException{

    public NoCountryFoundException(String message) {
        super(message);
    }

    public NoCountryFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
