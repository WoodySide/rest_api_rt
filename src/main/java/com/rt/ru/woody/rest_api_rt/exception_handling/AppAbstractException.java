package com.rt.ru.woody.rest_api_rt.exception_handling;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class AppAbstractException extends RuntimeException {

    public AppAbstractException(String message) {
        super(message);
    }
}
