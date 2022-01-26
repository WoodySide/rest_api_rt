package com.rt.ru.woody.rest_api_rt.request;


import lombok.extern.slf4j.Slf4j;
import org.apache.http.client.fluent.Content;
import org.apache.http.client.fluent.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

@Component
@Slf4j
@PropertySource("classpath:values.properties")
public class CountryRequest {

    @Value(value = "${url_countries}")
    private String countries;

    @Value(value = "${url_phones}")
    private String phones;

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
