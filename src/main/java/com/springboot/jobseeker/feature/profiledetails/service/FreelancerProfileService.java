package com.springboot.jobseeker.feature.profiledetails.service;

import com.springboot.jobseeker.feature.profiledetails.dto.FreelancerProfileRequest;
import com.springboot.jobseeker.feature.profiledetails.dto.FreelancerProfileResponse;

import java.util.List;

public interface FreelancerProfileService {
    FreelancerProfileResponse createFreelancerProfile(FreelancerProfileRequest createFreelancerProfileRequest, String loginUsername);
    FreelancerProfileResponse retrieveOne(Long id);
    List<FreelancerProfileResponse> retrieveAll();
    FreelancerProfileResponse updateFreelancerProfile(FreelancerProfileRequest updateFreelancerProfileRequest, Long id, String loginUsername);
    void deleteFreelancerById(Long id);
}
