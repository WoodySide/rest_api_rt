package com.rt.ru.woody.rest_api_rt.exception_handling;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NotAProperLinkException extends RuntimeException{

    public NotAProperLinkException(String message) {
        super(message);
    }

    public NotAProperLinkException(String message, Throwable cause) {
        super(message, cause);
    }
}
