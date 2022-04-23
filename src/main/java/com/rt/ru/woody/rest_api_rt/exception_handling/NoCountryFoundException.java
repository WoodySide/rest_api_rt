package com.rt.ru.woody.rest_api_rt.exception_handling;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NoCountryFoundException extends AppAbstractException{

    private static final String ERROR_MESSAGE = "There is no such country or this country has no code";

    public NoCountryFoundException() {
        super(ERROR_MESSAGE);
    }
}
