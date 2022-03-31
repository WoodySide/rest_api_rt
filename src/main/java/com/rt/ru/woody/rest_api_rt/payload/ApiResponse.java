package com.rt.ru.woody.rest_api_rt.payload;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;


@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
public class ApiResponse {

    private final String data;

    private final Boolean success;

    private final String timestamp;

    private final String cause;

    private final String path;

    public ApiResponse(Boolean success, String data, String cause, String path) {
        this.timestamp = getTimestamp();
        this.data = data;
        this.success = success;
        this.cause = null;
        this.path = null;
    }


    public ApiResponse(Boolean success, String data) {
        this.timestamp = getTimestamp();
        this.data = data;
        this.success = success;
        this.cause = null;
        this.path = null;
    }

    public String getTimestamp() {

        LocalDate localDate = LocalDate.now();

        LocalTime localTime = LocalTime.now();

        ZonedDateTime zonedDateTime = ZonedDateTime.of(localDate,localTime,
                ZoneId.of("Europe/Moscow"));

        return DateTimeFormatter.ofLocalizedDateTime(FormatStyle.LONG)
                .format(zonedDateTime);
    }
}