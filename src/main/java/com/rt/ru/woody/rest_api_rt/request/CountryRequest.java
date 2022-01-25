package com.rt.ru.woody.rest_api_rt.request;


import lombok.extern.slf4j.Slf4j;
import org.apache.http.client.fluent.Content;
import org.apache.http.client.fluent.Request;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@Slf4j
public class CountryRequest {

    private final String countries = "http://country.io/names.json";

    private final String phones = "http://country.io/phone.json";

    public String getCountriesContent() throws IOException {

        log.info("Getting json with countries in it");
        Content countriesContent = Request.Get(countries)
                .execute()
                .returnContent();

        return countriesContent.asString();
    }

    public String getCodeContent() throws IOException {

        log.info("Getting json with countries phone codes in it");
        Content phoneContent = Request.Get(phones)
                .execute()
                .returnContent();

        return phoneContent.asString();

    }
}
