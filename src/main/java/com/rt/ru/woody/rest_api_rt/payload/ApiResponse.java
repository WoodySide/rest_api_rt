package com.rt.ru.woody.rest_api_rt.payload;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;


@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
public class ApiResponse {

    private final String data;

    private final Boolean success;

    private final String cause;

    private final String path;

    public ApiResponse(Boolean success, String data, String cause, String path) {
        this.data = data;
        this.success = success;
        this.cause = null;
        this.path = null;
    }

    public ApiResponse(Boolean success, String data) {
        this.data = data;
        this.success = success;
        this.cause = null;
        this.path = null;
    }
}