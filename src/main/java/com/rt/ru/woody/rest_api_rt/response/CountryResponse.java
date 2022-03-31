package com.rt.ru.woody.rest_api_rt.response;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonPrimitive;
import com.rt.ru.woody.rest_api_rt.request.CountryRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Component
@Slf4j
public class CountryResponse {

    private final CountryRequest request;

    @Autowired
    public CountryResponse(CountryRequest request) {
        this.request = request;

    }

    public Map<String,String> countriesContent(String url) throws IOException {

        String jsonString = request.getRequest(url);

        JsonObject jsonObject = getResponse(jsonString);
        Set<String> strings = jsonObject.keySet();

        Map<String,String> map = new HashMap<>();

        for(String shorties: strings) {
            JsonPrimitive bd = jsonObject.getAsJsonPrimitive(shorties);

            map.put(shorties, bd.getAsString());
        }

        return map;
    }

    private JsonObject getResponse(String request) {
        JsonElement element = new JsonParser().parse(request);
        return element.getAsJsonObject();
    }
}
