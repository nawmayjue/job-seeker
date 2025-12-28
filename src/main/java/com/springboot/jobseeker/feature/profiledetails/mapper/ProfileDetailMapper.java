package com.springboot.jobseeker.feature.profiledetails.mapper;

import com.springboot.jobseeker.feature.country.dto.CountriesResponse;
import com.springboot.jobseeker.feature.profiledetails.dto.FreelancerProfileResponse;
import com.springboot.jobseeker.feature.profiledetails.dto.FreelancerProfileSummaryResponse;
import com.springboot.jobseeker.feature.user.dto.UserResponse;
import com.springboot.jobseeker.shared.data.model.ProfileDetail;
import org.springframework.stereotype.Component;

@Component
public class ProfileDetailMapper {
    private ProfileDetailMapper() {

    }

    public FreelancerProfileResponse toFreelancerProfileResponse(ProfileDetail profileDetail){
        return FreelancerProfileResponse.builder()
                .id(profileDetail.getId())
                .address(profileDetail.getAddress())
                .education(profileDetail.getEducation())
                .countryResponse(
                        CountriesResponse
                                .builder()
                                    .id(profileDetail.getCountry().getId())
                                    .name(profileDetail.getCountry().getName())
                                .build()
                )
                .userResponse(
                        UserResponse.builder()
                                .id(profileDetail.getUser().getId())
                                .fullName(profileDetail.getUser().getFullName())
                                .loginUsername(profileDetail.getUser().getLoginUsername())
                                .loginEmail(profileDetail.getUser().getLoginEmail())
                                .build()
                )
                .build();
    }

    public FreelancerProfileSummaryResponse toFreelancerProfileSummaryResponse(ProfileDetail profileDetail){
        return FreelancerProfileSummaryResponse.builder()
                .freelancerProfileResponse(
                        FreelancerProfileResponse.builder()
                                .id(profileDetail.getId())
                                .address(profileDetail.getAddress())
                                .education(profileDetail.getEducation())
                                .countryResponse(
                                        CountriesResponse
                                                .builder()
                                                .id(profileDetail.getCountry().getId())
                                                .name(profileDetail.getCountry().getName())
                                                .build()
                                )
                                .userResponse(
                                        UserResponse.builder()
                                                .id(profileDetail.getUser().getId())
                                                .fullName(profileDetail.getUser().getFullName())
                                                .loginUsername(profileDetail.getUser().getLoginUsername())
                                                .loginEmail(profileDetail.getUser().getLoginEmail())
                                                .build()
                                )
                                .build()
                )
                .cvUrl(profileDetail.getCvUrl())
                .summary(profileDetail.getSummary())
                .build();

    }
}
