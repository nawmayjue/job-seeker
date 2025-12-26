package com.springboot.jobseeker.feature.country.service.impl;

import com.springboot.jobseeker.feature.country.dto.CountriesResponse;
import com.springboot.jobseeker.feature.country.dto.CreateCountryRequest;
import com.springboot.jobseeker.feature.country.dto.UpdateCountryRequest;
import com.springboot.jobseeker.feature.country.service.CountryService;
import com.springboot.jobseeker.shared.data.model.Country;
import com.springboot.jobseeker.shared.data.repository.jpa.CountryJpaRepository;
import com.springboot.jobseeker.shared.exception.BadRequestException;
import com.springboot.jobseeker.shared.exception.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CountryServiceImpl implements CountryService {
    private final CountryJpaRepository countryJpaRepository;

    @Override
    public CountriesResponse createCountry(CreateCountryRequest request) {
        if (countryJpaRepository.existsByName(request.name())) {
            Country existingCountry = countryJpaRepository.findByName(request.name());
            if(existingCountry.getDeletedBy()!=null){
                if (existingCountry.getDeletedAt()!=null){
                    existingCountry.setDeletedBy(null);
                    existingCountry.setDeletedAt(null);
                    Country savedCountry = countryJpaRepository.save(existingCountry);

                    return CountriesResponse.builder()
                            .id(savedCountry.getId())
                            .name(savedCountry.getName())
                            .createdAt(savedCountry.getCreatedAt())
                            .updatedAt(savedCountry.getUpdatedAt())
                            .build();
                }
            }
            throw new BadRequestException("Country Already Exists.");
        }

        Country createdCountry = Country.builder()
                .name(request.name())
                .build();

        Country savedCountry = countryJpaRepository.save(createdCountry);

        return CountriesResponse.builder()
                .id(savedCountry.getId())
                .name(savedCountry.getName())
                .createdAt(savedCountry.getCreatedAt())
                .updatedAt(savedCountry.getUpdatedAt())
                .build();
    }

    @Override
    public List<CountriesResponse> retrieveAll() {
        List<Country> countries = countryJpaRepository.findAll();
        return countries.stream()
                .filter(country ->
                        country.getDeletedAt() == null &&
                                country.getDeletedBy() == null
                )
                .map(country -> {
                            CountriesResponse response = CountriesResponse.builder()
                                    .id(country.getId())
                                    .name(country.getName())
                                    .createdAt(country.getCreatedAt())
                                    .updatedAt(country.getUpdatedAt())
                                    .build();
                            return response;
                        }
                )
                .toList();
    }

    @Override
    public CountriesResponse retrieveOne(Long id) {
        Country country = countryJpaRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Country Not Found."));
        if(country.getDeletedBy()!=null){
            if (country.getDeletedAt()!=null){
                throw new BadRequestException("This country has been moved to recycle bin");
            }
        }
        return CountriesResponse.builder()
                .id(country.getId())
                .name(country.getName())
                .createdAt(country.getCreatedAt())
                .updatedAt(country.getUpdatedAt())
                .build();
    }

    @Override
    public CountriesResponse updateCountry(Long id, UpdateCountryRequest request) {
        Country country = countryJpaRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Country Not Found."));

        if (countryJpaRepository.existsByName(request.name())) {

            if (country.getDeletedBy() != null) {
                if (country.getDeletedAt() != null) {
                    throw new BadRequestException("This country has been moved to recycle bin");
                }
            }

            throw new BadRequestException("Country Already Exists.");
        }

        Country updatedCountry = country.builder()
                .id(id)
                .name(request.name())
                .build();

        Country savedCountry = countryJpaRepository.save(updatedCountry);

        return CountriesResponse.builder()
                .id(savedCountry.getId())
                .name(savedCountry.getName())
                .createdAt(savedCountry.getCreatedAt())
                .updatedAt(savedCountry.getUpdatedAt())
                .build();
    }

    @Override
    public void delete(Long id) {
        Country country = countryJpaRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Country Not Found."));

        if(country.getDeletedBy()!=null){
            if (country.getDeletedAt()!=null){
                throw new BadRequestException("This country has already been moved to recycle bin");
            }
        }
        country.delete(1L);
        countryJpaRepository.save(country);

    }
}
