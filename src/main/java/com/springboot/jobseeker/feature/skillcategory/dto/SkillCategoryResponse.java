package com.springboot.jobseeker.feature.skillcategory.dto;

import com.springboot.jobseeker.shared.data.dto.MasterDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@SuperBuilder
@Getter
@AllArgsConstructor
public class SkillCategoryResponse extends MasterDto {
    private Long id;
    private String name;

}
