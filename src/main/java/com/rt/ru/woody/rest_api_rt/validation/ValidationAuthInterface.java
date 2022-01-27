package com.rt.ru.woody.rest_api_rt.validation;

public interface ValidationAuthInterface {

    void checkHeader(String checkHeader, String header);

    void notEmptyCode(String telephone_code);
}
