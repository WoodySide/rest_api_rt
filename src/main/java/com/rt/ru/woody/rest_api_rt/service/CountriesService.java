package com.rt.ru.woody.rest_api_rt.service;

import com.rt.ru.woody.rest_api_rt.model.Countries;
import com.rt.ru.woody.rest_api_rt.repository.CountryRepository;
import com.rt.ru.woody.rest_api_rt.response.CountryResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Slf4j
public class CountriesService {

    private final CountryRepository countryRepository;

    private final RestTemplate restTemplate;

    private final CountryResponse countryResponse;


    @Autowired
    public CountriesService(CountryRepository countryRepository, RestTemplate restTemplate, CountryResponse countryResponse) {
        this.countryRepository = countryRepository;
        this.restTemplate = restTemplate;
        this.countryResponse = countryResponse;
    }


    public void saveDataToDB() throws IOException {
        Map<String,String> map = countryResponse.gsonParser();

        System.out.println(map.entrySet());

        List<Countries> countries = new ArrayList<>();

        for(Map.Entry<String,String> entry: map.entrySet()) {
            countries.add(new Countries(entry.getKey(),entry.getValue()));
        }

        List<Countries> finalCountriesNames = countries.stream()
                        .sorted(Comparator.comparing(Countries::getShortName))
                .collect(Collectors.toList());

        countryRepository.saveAll(finalCountriesNames);
    }


}
