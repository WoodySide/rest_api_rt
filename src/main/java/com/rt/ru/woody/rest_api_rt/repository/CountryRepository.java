package com.rt.ru.woody.rest_api_rt.repository;

import com.rt.ru.woody.rest_api_rt.model.Countries;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface CountryRepository extends JpaRepository<Countries, Long> {

     List<Countries> getTopByFullNameContainingIgnoreCase(String fullName);
}
