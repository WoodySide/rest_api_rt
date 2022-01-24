package com.rt.ru.woody.rest_api_rt;

import com.rt.ru.woody.rest_api_rt.request.CountryRequest;
import com.rt.ru.woody.rest_api_rt.response.CountryResponse;
import com.rt.ru.woody.rest_api_rt.service.CountriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class RestApiRtApplication implements CommandLineRunner{

    @Autowired
    private CountryResponse response;

    @Autowired
    private CountryRequest request;

    @Autowired
    private CountriesService countriesService;


    public static void main(String[] args) {
        SpringApplication.run(RestApiRtApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
//        response.gsonParser();
//        request.generate();
        countriesService.saveDataToDB();
    }
}
