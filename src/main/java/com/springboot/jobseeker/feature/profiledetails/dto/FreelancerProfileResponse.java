package com.springboot.jobseeker.feature.profiledetails.dto;

import com.springboot.jobseeker.feature.country.dto.CountriesResponse;
import com.springboot.jobseeker.feature.user.dto.UserResponse;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FreelancerProfileResponse {
    private Long id;
    private UserResponse userResponse;
    private String address;
    private String education;
    private CountriesResponse countryResponse;
}
