package com.rt.ru.woody.rest_api_rt.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/v1")
public class RestAppController {


    @PostMapping(path = "/reload")
    public void reloadData() {

    }

    @GetMapping(path = "/code/{countryName}")
    public void getCountryCode(@PathVariable(value = "countryName") String countryName) {

    }
}
