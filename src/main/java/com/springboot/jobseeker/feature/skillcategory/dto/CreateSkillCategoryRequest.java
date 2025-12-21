package com.springboot.jobseeker.feature.skillcategory.dto;

import jakarta.validation.constraints.NotBlank;

public record CreateSkillCategoryRequest(
        @NotBlank(message = "Name is required")
        String name
) {}
