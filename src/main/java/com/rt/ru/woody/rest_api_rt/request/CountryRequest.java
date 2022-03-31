package com.rt.ru.woody.rest_api_rt.request;


import com.rt.ru.woody.rest_api_rt.exception_handling.NotAProperLinkException;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.client.fluent.Content;
import org.apache.http.client.fluent.Request;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;


import java.io.IOException;

@Component
@Slf4j
public class CountryRequest {

    public String getRequest(String url) throws IOException {

        Content countriesContent;

        try {
            log.info("Getting json with countries in it");
            countriesContent = Request.Get(url)
                    .execute()
                    .returnContent();

        } catch (IOException e) {
            throw new NotAProperLinkException("Link doesn't seem correct!");
        }

        return countriesContent.asString();
    }
}
