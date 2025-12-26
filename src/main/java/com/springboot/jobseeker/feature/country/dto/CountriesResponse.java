package com.springboot.jobseeker.feature.country.dto;

import lombok.Builder;

@Builder
public record CountriesResponse(
        Long id,
        String name
) {}
