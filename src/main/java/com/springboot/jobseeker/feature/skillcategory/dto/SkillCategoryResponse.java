package com.springboot.jobseeker.feature.skillcategory.dto;

import lombok.Builder;

@Builder
public record SkillCategoryResponse(
        Long id,
        String name
) {}
