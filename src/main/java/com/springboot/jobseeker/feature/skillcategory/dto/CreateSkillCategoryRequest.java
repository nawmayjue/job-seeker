package com.springboot.jobseeker.feature.skillcategory.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

public record CreateSkillCategoryRequest(
        @NotBlank(message = "Name is required")
        String name
) {
        @Override
        public String name() {
                return name;
        }
}
