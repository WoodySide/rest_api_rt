package com.rt.ru.woody.rest_api_rt.repository;

import com.rt.ru.woody.rest_api_rt.TraceUnitTestRule;
import com.rt.ru.woody.rest_api_rt.model.Countries;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
@ExtendWith(TraceUnitTestRule.class)
@ExtendWith(SpringExtension.class)
@Profile(value = "test")
public class CountryRepositoryTest {

    @Autowired
    private TestEntityManager testEntityManager;

    @Autowired
    private CountryRepository countryRepository;


    private Countries country;

    @BeforeEach
    public void setUp() {
        country = Countries
                .builder()
                .shortName("AU")
                .fullName("Austria")
                .fullNameToLower("austria")
                .build();

        testEntityManager.persist(country);
    }

    @AfterEach
    public void clear() {
        countryRepository.deleteAll();;
    }

    @Test
    public void whenGetTopByFullNameOrFullNameToLower_thenReturnCountrie() {

        Optional<Countries> found = countryRepository.getTopByFullNameOrFullNameToLower("Austria", "austria");

        assertThat(found.get()).isEqualTo(country);
    }
}
