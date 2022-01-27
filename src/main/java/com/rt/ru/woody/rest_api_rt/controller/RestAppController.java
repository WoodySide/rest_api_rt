package com.rt.ru.woody.rest_api_rt.controller;

import com.rt.ru.woody.rest_api_rt.model.Countries;
import com.rt.ru.woody.rest_api_rt.service.CountriesService;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api/v1")
@PropertySource("classpath:values.properties")
@Getter
public class RestAppController {

    private CountriesService countriesService;

    @Value("${secret_code}")
    private  String SECURED_NUMBER_CODE;

    @Value("${secret_reload}")
    private  String SECURED_NUMBER_RELOAD;

    @Autowired
    public RestAppController(CountriesService countriesService) {
        this.countriesService = countriesService;
    }


    public RestAppController() {
    }

    @PostMapping(path = "/reload", produces = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<String> reloadData(@RequestHeader(value="Authorization",required = true)
                                                          String header) throws IOException, NoSuchFieldException, IllegalAccessException {

        countriesService.checkHeader(SECURED_NUMBER_RELOAD, header);

        countriesService.saveDataToDB();

        return ResponseEntity
                .ok()
                .body("Data was successfully reloaded! \n You can check it right here --> http://localhost:9090/h2-console/login.jsp?jsessionid=aef4b5e43e06e8be91b91603ce4930aa");
    }

    @GetMapping(path = "/code/{countryName}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getCountryCode(@PathVariable(value = "countryName") String countryName,
                                                 @RequestHeader(value="Authorization",required = true)
                                                         String header) {

        countriesService.checkHeader(SECURED_NUMBER_CODE,header);

        Optional<Countries> optionalCountries =
                countriesService.getByCountryName(countryName);


        String telephone_code = optionalCountries
                .stream()
                .map(Countries::getPhoneCodes)
                .collect(Collectors.joining());

       countriesService.notEmptyCode(telephone_code);
        return ResponseEntity.ok()
                .body("Telephone code of the country is: " + telephone_code);
    }

    @GetMapping(path = "/remove")
    public String deleteData() {

        countriesService.removeDataFromDB();

        return "All data has been deleted";
    }
}