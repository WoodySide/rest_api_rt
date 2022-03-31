package com.rt.ru.woody.rest_api_rt.service;

import com.rt.ru.woody.rest_api_rt.model.Countries;
import com.rt.ru.woody.rest_api_rt.repository.CountryRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class CachedServiceCountries {

    private final CountryRepository countryRepository;

    @Autowired
    public CachedServiceCountries(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    @Cacheable(value = "country_data", key = "{#countryName, #countryNameToLower}")
    public Optional<Countries> getByCountryName(String countryName, String countryNameToLower) {
        return countryRepository.getTopByFullNameOrFullNameToLower(countryName,countryNameToLower);
    }

    @CacheEvict(allEntries = true, value="country_data")
    @Scheduled(fixedRate = 3600000, initialDelay = 3600000) // reset cache every hr, with delay of 1hr
    public void reportCacheEvictingTime() {
        LocalDate anotherSummerDay = LocalDate.now();
        LocalTime anotherTime = LocalTime.now();
        ZonedDateTime zonedDateTime = ZonedDateTime.of(anotherSummerDay, anotherTime, ZoneId.of("Europe/Moscow"));
        log.info("Clearing all cache, time: " + DateTimeFormatter.ofLocalizedDateTime(FormatStyle.LONG)
                .format(zonedDateTime) );
    }
}
