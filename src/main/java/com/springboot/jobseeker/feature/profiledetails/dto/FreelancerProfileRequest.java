package com.springboot.jobseeker.feature.profiledetails.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FreelancerProfileRequest {
    private String address;
    private String education;
    private Long countryId;
}
