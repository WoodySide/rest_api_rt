package com.rt.ru.woody.rest_api_rt.payload;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;

import java.time.Instant;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
public class ApiResponse {

    private final String data;

    private final Boolean success;

    @JsonIgnore
    private final String timestamp;

    private final String cause;

    private final String path;

    public ApiResponse(Boolean success, String data, String cause, String path) {
        this.timestamp = Instant.now().toString();
        this.data = data;
        this.success = success;
        this.cause = null;
        this.path = null;
    }

    public ApiResponse(Boolean success, String data) {
        this.timestamp = Instant.now().toString();
        this.data = data;
        this.success = success;
        this.cause = null;
        this.path = null;
    }
}