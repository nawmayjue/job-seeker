package com.springboot.jobseeker.feature.country.dto;

import com.springboot.jobseeker.shared.data.dto.MasterDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@AllArgsConstructor
@Getter
public class CountriesResponse extends MasterDto{
    private Long id;
    private String name;
}
