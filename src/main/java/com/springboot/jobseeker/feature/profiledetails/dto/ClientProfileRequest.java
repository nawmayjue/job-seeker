package com.springboot.jobseeker.feature.profiledetails.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ClientProfileRequest {
    private String address;
    private String companyName;
    private Long countryId;
}
