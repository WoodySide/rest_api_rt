package com.rt.ru.woody.rest_api_rt.service;

import com.rt.ru.woody.rest_api_rt.model.Countries;
import com.rt.ru.woody.rest_api_rt.repository.CountryRepository;
import com.rt.ru.woody.rest_api_rt.response.CountryResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Slf4j
public class CountriesService {

    private final CountryRepository countryRepository;

    private final CountryResponse countryResponse;


    @Autowired
    public CountriesService(CountryRepository countryRepository, CountryResponse countryResponse) {
        this.countryRepository = countryRepository;
        this.countryResponse = countryResponse;
    }


    @Scheduled(cron = "0 0 2 * * ?", zone = "Europe/Moscow")
    @Transactional
    public void saveDataToDB() throws IOException {
        Map<String,String> countriesMap = countryResponse.countriesContent();

        Map<String,String> codeMap = countryResponse.codeContent();

        List<Countries> countries = new ArrayList<>();

        for(Map.Entry<String,String> entry1: countriesMap.entrySet()) {
            for(Map.Entry<String,String> entry2: codeMap.entrySet()) {
                if(entry1.getKey().equals(entry2.getKey())) {
                    countries.add(new Countries(entry1.getKey(), entry1.getValue(),
                            entry2.getValue()));
                }
            }
        }

        List<Countries> finalCountriesNames = countries.stream()
                        .sorted(Comparator.comparing(Countries::getShortName))
                .collect(Collectors.toList());


        countryRepository.saveAll(finalCountriesNames);
    }

    public Optional<Countries> getByCountryName(String countryName) {
        return countryRepository.getByFullNameContainingIgnoreCase(countryName);
    }
}
