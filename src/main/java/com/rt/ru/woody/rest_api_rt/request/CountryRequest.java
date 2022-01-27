package com.rt.ru.woody.rest_api_rt.request;


import com.rt.ru.woody.rest_api_rt.exception_handling.NotAProperLinkException;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpResponseFactory;
import org.apache.http.client.fluent.Content;
import org.apache.http.client.fluent.Request;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;


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

        Content countriesContent = null;

        try {
            log.info("Getting json with countries in it");
            countriesContent = Request.Get(countries)
                    .execute()
                    .returnContent();

        } catch (IOException e) {
            throw new NotAProperLinkException("Link doesn't seem correct!");
        }

        return countriesContent.asString();
    }

    public String getCodeContent() throws IOException {

        Content phoneContent = null;

        try {
        log.info("Getting json with countries phone codes in it");
        phoneContent = Request.Get(phones)
                .execute()
                .returnContent();

        } catch (IOException e) {
            throw new NotAProperLinkException("Link doesn't seem correct!");
        }

        return phoneContent.asString();

    }
}
