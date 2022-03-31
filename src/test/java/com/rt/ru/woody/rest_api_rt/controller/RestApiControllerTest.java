package com.rt.ru.woody.rest_api_rt.controller;


import com.rt.ru.woody.rest_api_rt.model.AppConst;
import com.rt.ru.woody.rest_api_rt.service.CountriesService;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.HttpHeaders;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.IOException;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class RestApiControllerTest {

    private static final String CODE_URL = "http://localhost:9090/api/v1/code";

    private static final String RELOAD_URL = "http://localhost:9090/api/v1/reload";

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private CountriesService countriesService;

    @Before
    public void setUp() throws IOException {
        countriesService.saveDataToDB();
    }

    private void getCodeByCountryName() throws Exception {
        String countryName = "Austria";
        mockMvc
                .perform(
                        get(CODE_URL + "/" + countryName)
                        .header(HttpHeaders.AUTHORIZATION, AppConst.SECURED_NUMBER_CODE)
                )
                .andExpect(status().isOk())
                .andDo(print());
    }

    private void getCodeByCountryNameWithWrongBearer(String countryName) throws Exception {
        mockMvc
                .perform(
                        get(CODE_URL + "/" + countryName)
                                .header(HttpHeaders.AUTHORIZATION, "Bearer 123")
                )
                .andExpect(status().isForbidden())
                .andDo(print())
                .andExpect(content()
                        .json("{\"data\":" +
                                "\"The entrance is forbidden. No rights to see the data.\"," +
                                    "\"success\":false}"));
    }

    private void getCodeByCountryNameWithoutBearer(String countryName) throws Exception {
        mockMvc
                .perform(
                        get(CODE_URL + "/" + countryName))
                .andExpect(status().isBadRequest())
                .andDo(print());
    }

    private void getCodeByIncorrectCountryNameOrRandomNumber(String countryName) throws Exception {
        mockMvc
                .perform(
                        get(CODE_URL + "/" + countryName)
                .header(HttpHeaders.AUTHORIZATION, AppConst.SECURED_NUMBER_CODE))
                .andExpect(status().isNotFound())
                .andDo(print());
    }

    private void reloadDataToDB() throws Exception {
        mockMvc
                .perform(
                        post(RELOAD_URL)
                                .header(HttpHeaders.AUTHORIZATION, AppConst.SECURED_NUMBER_RELOAD))
                .andExpect(status().isOk())
                .andDo(print());
    }

    private void reloadDataToDBWithWrongBearer() throws Exception {
        mockMvc
                .perform(
                        post(RELOAD_URL)
                                .header(HttpHeaders.AUTHORIZATION, "Bearer 123"))
                .andExpect(status().isForbidden())
                .andDo(print());
    }

    private void reloadDataToDBWithoutBearer() throws Exception {
        mockMvc
                .perform(
                        post(RELOAD_URL))
                .andExpect(status().isBadRequest())
                .andDo(print());
    }

    @Test
    public void whenGetCountryName_thenReturnItsCode() throws Exception {
        getCodeByCountryName();
    }

    @Test
    public void whenGetCountryNameWithWrongBearer_thenReturnIsForbidden() throws Exception {
        getCodeByCountryNameWithWrongBearer("Austria");
    }

    @Test
    public void whenGetCountryNameWithoutBearer_thenReturnIsBadRequest() throws Exception {
        getCodeByCountryNameWithoutBearer("Austria");
    }

    @Test
    public void whenGetCountryWithIncorrectNameOrRandomNumber_thenReturnIsNotFound() throws Exception {
        getCodeByIncorrectCountryNameOrRandomNumber("Austrias");
    }


    @Test
    public void whenReloadDataToDB_thenReturnSuccessfulMessage() throws Exception {
       reloadDataToDB();
    }

    @Test
    public void whenReloadDataToDBWithWrongBearer_thenReturnIsForbidden() throws Exception {
       reloadDataToDBWithWrongBearer();
    }

    @Test
    public void whenReloadDataToDBWithoutBearer_thenReturnIsBadRequest() throws Exception {
        reloadDataToDBWithoutBearer();
    }
}
