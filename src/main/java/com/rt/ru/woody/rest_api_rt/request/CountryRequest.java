package com.rt.ru.woody.rest_api_rt.request;

import com.rt.ru.woody.rest_api_rt.utils.FileSaver;
import org.apache.http.client.fluent.Content;
import org.apache.http.client.fluent.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class CountryRequest {

    private final String countries = "http://country.io/names.json";

    private final String phones = "http://country.io/phone.json";

    @Autowired
    private FileSaver fileSaver;

    public String generate() throws IOException {

        StringBuilder builder = new StringBuilder();

        Content countriesContent = Request.Get(countries)
                .execute()
                .returnContent();

        Content phoneContent = Request.Get(phones)
                .execute()
                .returnContent();

        builder.append(countriesContent);

        return builder.toString();
    }
}
