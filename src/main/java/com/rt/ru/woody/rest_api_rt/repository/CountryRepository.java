package com.rt.ru.woody.rest_api_rt.repository;

import com.rt.ru.woody.rest_api_rt.model.Countries;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CountryRepository extends JpaRepository<Countries, Long> {

     Optional<Countries> getByFullNameContainingIgnoreCase(String fullName);

     Optional<Countries> deleteByFullName(String fullName);
}
