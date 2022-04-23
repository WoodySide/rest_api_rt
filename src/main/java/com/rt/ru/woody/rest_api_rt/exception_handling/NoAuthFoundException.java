package com.rt.ru.woody.rest_api_rt.exception_handling;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class NoAuthFoundException extends AppAbstractException{

    private static final String ERROR_MESSAGE = "The entrance is forbidden. No rights to see the data.";

    public NoAuthFoundException() {
        super(ERROR_MESSAGE);
    }
}
