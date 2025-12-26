package com.springboot.jobseeker.feature.country.service;



import com.springboot.jobseeker.feature.country.dto.CountriesResponse;
import com.springboot.jobseeker.feature.country.dto.CreateCountryRequest;
import com.springboot.jobseeker.feature.country.dto.UpdateCountryRequest;

import java.util.List;

public interface CountryService {
    CountriesResponse createCountry(CreateCountryRequest request);
    List<CountriesResponse> retrieveAll();
    CountriesResponse retrieveOne(Long id);
    CountriesResponse updateCountry(Long id, UpdateCountryRequest request);
    void delete(Long id);
}
