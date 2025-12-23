package com.springboot.jobseeker.feature.skillcategory.dto;

import jakarta.validation.constraints.NotBlank;

public record UpdateSkillCategoryRequest(
        @NotBlank(message = "Name is required")
        String name
) {
        @Override
        public String name() {
                return name;
        }
}
