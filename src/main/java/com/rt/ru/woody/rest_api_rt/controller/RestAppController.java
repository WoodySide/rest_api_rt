package com.rt.ru.woody.rest_api_rt.controller;

import com.rt.ru.woody.rest_api_rt.model.Countries;
import com.rt.ru.woody.rest_api_rt.service.CachedServiceCountries;
import com.rt.ru.woody.rest_api_rt.service.CountriesService;
import com.rt.ru.woody.rest_api_rt.validation.ValidationAuthInterface;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api/v1")
@PropertySource("classpath:values.properties")
@Getter
public class RestAppController {

    private final CountriesService countriesService;

    private final HttpServletRequest request;

    private final CachedServiceCountries cachedCountries;

    @Value("${secret_code}")
    private String SECURED_NUMBER_CODE;

    @Value("${secret_reload}")
    private String SECURED_NUMBER_RELOAD;

    @Autowired
    public RestAppController(CountriesService countriesService, HttpServletRequest request, CachedServiceCountries cachedCountries) {
        this.countriesService = countriesService;
        this.request = request;
        this.cachedCountries = cachedCountries;
    }

    @PostConstruct
    public void setUpDB() throws IOException {
        countriesService.saveDataToDB();
    }

    @PostMapping(path = "/reload", produces = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<String> reloadData() throws IOException {


        Map<String,String> header = getHeadersInfo();

        header.put("Authorization", SECURED_NUMBER_RELOAD);

        countriesService.saveDataToDB();

        return ResponseEntity
                .ok()
                .body("Data was successfully reloaded! \n You can check it right here --> http://localhost:9090/h2-console/login.jsp?jsessionid=aef4b5e43e06e8be91b91603ce4930aa");
    }

    @GetMapping(path = "/code/{countryName}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getCountryCode(@PathVariable(value = "countryName") String countryName) throws IOException {

        Map<String,String> header = getHeadersInfo();

        header.put("Authorization", SECURED_NUMBER_CODE);

        countriesService.checkHeader(SECURED_NUMBER_CODE,header.get("authorization"));

        List<Countries> optionalCountries =
                cachedCountries.getByCountryName(countryName);

        String telephone_code = optionalCountries
                .stream()
                .map(Countries::getPhoneCodes)
                .collect(Collectors.joining());

        countriesService.checkCountryName(telephone_code);

        return ResponseEntity.ok()
                .body("Telephone code of the country is: " + telephone_code);
    }

    @GetMapping(path = "/remove")
    public String deleteData() {

        countriesService.removeDataFromDB();

        return "All data has been deleted";
    }

    /*
    Test cache get all
     */
    @GetMapping(path = "/get")
    public String getAllFromCache() {

        List<Countries> data = cachedCountries.getAllDataFromCache();

        return data.toString();
    }

    private Map<String, String> getHeadersInfo() {

        Map<String, String> map = new HashMap<>();

        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String key = headerNames.nextElement();
            String value = request.getHeader(key);
            map.put(key, value);

        }

        return map;
    }
}