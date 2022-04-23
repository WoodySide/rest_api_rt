package com.rt.ru.woody.rest_api_rt.controller;

import com.rt.ru.woody.rest_api_rt.exception_handling.NoCountryFoundException;
import com.rt.ru.woody.rest_api_rt.model.AppConst;
import com.rt.ru.woody.rest_api_rt.model.Countries;
import com.rt.ru.woody.rest_api_rt.payload.ApiResponse;
import com.rt.ru.woody.rest_api_rt.service.CachedServiceCountries;
import com.rt.ru.woody.rest_api_rt.service.CountriesService;
import com.rt.ru.woody.rest_api_rt.validation.annotation.Authorized;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.*;

@RestController
@RequestMapping(value = "/api/v1")
public class RestAppController {

    private final CountriesService countriesService;

    private final CachedServiceCountries cachedCountries;

    @Autowired
    public RestAppController(CountriesService countriesService, CachedServiceCountries cachedCountries) {
        this.countriesService = countriesService;
        this.cachedCountries = cachedCountries;
    }

    @PostConstruct
    public void setUpDB() throws IOException {
        countriesService.saveDataToDB();
    }

    @PostMapping(path = "/reload", produces = MediaType.APPLICATION_JSON_VALUE)
    @Authorized(code = AppConst.SECURED_NUMBER_RELOAD)
    public ResponseEntity<ApiResponse> reloadData() throws IOException {

        countriesService.saveDataToDB();

        return ResponseEntity
                .ok()
                .body(new ApiResponse(true,"Data was successfully reloaded! \n You can check it right here --> http://localhost:9090/h2-console/login.jsp?jsessionid=aef4b5e43e06e8be91b91603ce4930aa"));
    }

    @GetMapping(path = "/code/{countryName}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Authorized(code = AppConst.SECURED_NUMBER_CODE)
    public ResponseEntity<ApiResponse> getCountryByCode(@PathVariable(value = "countryName") String countryName) throws IOException {

        Optional<Countries> foundCountry =
                Optional.ofNullable(cachedCountries.getByCountryName(countryName, countryName.toLowerCase(Locale.ROOT))
                        .orElseThrow(NoCountryFoundException::new));

        return ResponseEntity
                .ok()
                .body(new ApiResponse(true, "Telephone code of the country is: +" + foundCountry.get().getPhoneCode()));
    }
}