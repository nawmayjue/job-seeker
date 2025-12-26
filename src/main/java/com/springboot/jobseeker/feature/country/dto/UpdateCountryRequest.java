package com.springboot.jobseeker.feature.country.dto;

import jakarta.validation.constraints.NotBlank;

public record UpdateCountryRequest(
        @NotBlank(message = "Name is required")
        String name
) {
        @Override
        public String name() {
                return name;
        }
}
