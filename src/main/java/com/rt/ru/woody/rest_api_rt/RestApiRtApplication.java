package com.rt.ru.woody.rest_api_rt;

import com.rt.ru.woody.rest_api_rt.response.CountryResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class RestApiRtApplication  {

    @Autowired
    private CountryResponse response;

    public static void main(String[] args) {
        SpringApplication.run(RestApiRtApplication.class, args);
    }

//    @Override
//    public void run(String... args) throws Exception {
//
//    }
}
