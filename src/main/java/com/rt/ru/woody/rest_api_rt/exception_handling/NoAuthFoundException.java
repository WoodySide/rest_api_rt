package com.rt.ru.woody.rest_api_rt.exception_handling;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class NoAuthFoundException extends RuntimeException{

    public NoAuthFoundException(String message) {
        super(message);
    }

    public NoAuthFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
