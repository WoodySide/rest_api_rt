package com.rt.ru.woody.rest_api_rt.exception_handling;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NotAProperLinkException extends AppAbstractException{

    private static final String ERROR_MESSAGE = "Link doesn't seem correct!";

    public NotAProperLinkException() {
        super(ERROR_MESSAGE);
    }
}
