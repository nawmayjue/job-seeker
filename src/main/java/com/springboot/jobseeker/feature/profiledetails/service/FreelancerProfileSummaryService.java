package com.springboot.jobseeker.feature.profiledetails.service;

import com.springboot.jobseeker.feature.profiledetails.dto.FreelancerProfileSummaryRequest;
import com.springboot.jobseeker.feature.profiledetails.dto.FreelancerProfileSummaryResponse;

import java.util.List;

public interface FreelancerProfileSummaryService{
    FreelancerProfileSummaryResponse createFreelancerProfileSummary(FreelancerProfileSummaryRequest freelancerProfileSummaryRequest, Long freelancerProfileId, String loginUsername);
    List<FreelancerProfileSummaryResponse> retrieveAll();
    FreelancerProfileSummaryResponse retrieveOne(Long id);
    FreelancerProfileSummaryResponse updateFreelancerSummary(Long id, FreelancerProfileSummaryRequest freelancerProfileSummaryRequest, String loginUsername);
    void deleteFreelancerProfileSummary(Long id);
}
