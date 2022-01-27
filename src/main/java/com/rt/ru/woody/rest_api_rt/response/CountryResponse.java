package com.rt.ru.woody.rest_api_rt.response;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonPrimitive;
import com.rt.ru.woody.rest_api_rt.model.CountriesShort;
import com.rt.ru.woody.rest_api_rt.request.CountryRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component
@Slf4j
public class CountryResponse {

    private final CountryRequest request;

    @Autowired
    public CountryResponse(CountryRequest request) {
        this.request = request;

    }

    public Map<String,String>  countriesContent() throws IOException {

        String jsonString = request.getCountriesContent();

        JsonObject jsonObject = getResponse(jsonString);

        Map<String,String> map = new HashMap<>();

        for(CountriesShort shorties: CountriesShort.values()) {
            JsonPrimitive bd = jsonObject.getAsJsonPrimitive(shorties.toString());

            map.put(shorties.name(), bd.getAsString());
        }
        return map;
    }

    public Map<String,String> codeContent() throws IOException {

        String jsonString = request.getCodeContent();

        JsonObject jsonObject = getResponse(jsonString);

        Map<String,String> map = new HashMap<>();

        for(CountriesShort shorties: CountriesShort.values()) {
            JsonPrimitive bd = jsonObject.getAsJsonPrimitive(shorties.toString());

            map.put(shorties.name(), bd.getAsString());
        }

        return map;
    }

    private JsonObject getResponse(String request) {
        JsonElement element = new JsonParser().parse(request);
        return element.getAsJsonObject();
    }

}
