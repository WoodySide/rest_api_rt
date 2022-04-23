package com.rt.ru.woody.rest_api_rt.service;

import com.rt.ru.woody.rest_api_rt.model.AppConst;
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

        Map<String,String> countriesMap = countryResponse.countriesContent(AppConst.URL_COUNTRIES);

        Map<String,String> codeMap = countryResponse.countriesContent(AppConst.URL_CODES);

        List<Countries> countries = new ArrayList<>();

        countriesMap.forEach((key, value) -> codeMap.forEach((key1, value1) -> {
            if (key.equals(key1)) {
                countries.add(new Countries(key, value,value.toLowerCase(Locale.ROOT),
                        value1));
            }
        }));

        List<Countries> finalCountriesNames = countries.stream()
                        .sorted(Comparator.comparing(Countries::getShortName))
                .collect(Collectors.toList());

        log.info("Saving countries data to DB");

        countryRepository.saveAll(finalCountriesNames);
    }
}
