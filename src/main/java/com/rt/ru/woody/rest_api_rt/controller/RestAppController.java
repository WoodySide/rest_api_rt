package com.rt.ru.woody.rest_api_rt.controller;

import com.rt.ru.woody.rest_api_rt.exception_handling.NoAuthFoundException;
import com.rt.ru.woody.rest_api_rt.exception_handling.NoCountryFoundException;
import com.rt.ru.woody.rest_api_rt.model.Countries;
import com.rt.ru.woody.rest_api_rt.service.CountriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api/v1")
@PropertySource("classpath:values.properties")
public class RestAppController {

    private final CountriesService countriesService;

    @Value("${secret_code}")
    private String SECURED_NUMBER_CODE;

    @Value("${secret_reload}")
    private String SECURED_NUMBER_RELOAD;

    @Autowired
    public RestAppController(CountriesService countriesService) {
        this.countriesService = countriesService;
    }

    @PostMapping(path = "/reload", produces = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<String> reloadData(@RequestHeader(value="Authorization",required = true)
                                                          String header) throws IOException {

        if(!header.equals(SECURED_NUMBER_RELOAD)) {
            throw new NoAuthFoundException("The entrance is forbidden. No rights to reload the data.");
        }

        countriesService.saveDataToDB();

        return ResponseEntity
                .ok()
                .body("Data was successfully reloaded!");
    }

    @GetMapping(path = "/code/{countryName}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getCountryCode(@PathVariable(value = "countryName") String countryName,
                                                 @RequestHeader(value="Authorization",required = true)
                                                              String header){

        if(!header.equals(SECURED_NUMBER_CODE)) {
            throw new NoAuthFoundException("The entrance is forbidden. No rights to see the data.");
        }

        Optional<Countries> optionalCountries =
                countriesService.getByCountryName(countryName);

        String telephone_code = optionalCountries
                .stream()
                .map(Countries::getPhoneCodes)
                .collect(Collectors.joining());

        if(telephone_code.isEmpty()) {
            throw  new NoCountryFoundException("There is not such country.Please try again, " +
                    "and pay more attention next time.");
        }

        return ResponseEntity.ok()
                .body("Telephone code of the country is: " + telephone_code);
    }
}
