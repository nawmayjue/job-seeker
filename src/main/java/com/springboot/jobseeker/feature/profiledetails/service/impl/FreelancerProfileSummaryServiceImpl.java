package com.springboot.jobseeker.feature.profiledetails.service.impl;

import com.springboot.jobseeker.feature.profiledetails.dto.FreelancerProfileResponse;
import com.springboot.jobseeker.feature.profiledetails.dto.FreelancerProfileSummaryRequest;
import com.springboot.jobseeker.feature.profiledetails.dto.FreelancerProfileSummaryResponse;
import com.springboot.jobseeker.feature.profiledetails.mapper.ProfileDetailMapper;
import com.springboot.jobseeker.feature.profiledetails.service.FreelancerProfileSummaryService;
import com.springboot.jobseeker.feature.user.repository.jpa.UserJpaRepository;
import com.springboot.jobseeker.shared.data.model.ProfileDetail;
import com.springboot.jobseeker.shared.data.model.User;
import com.springboot.jobseeker.shared.data.repository.jpa.ProfileDetailJpaRepository;
import com.springboot.jobseeker.shared.exception.BadRequestException;
import com.springboot.jobseeker.shared.exception.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class FreelancerProfileSummaryServiceImpl implements FreelancerProfileSummaryService {
    private final ProfileDetailJpaRepository profileDetailJpaRepository;
    private final ProfileDetailMapper profileDetailMapper;
    private final UserJpaRepository userJpaRepository;

    @Override
    public FreelancerProfileSummaryResponse createFreelancerProfileSummary(FreelancerProfileSummaryRequest freelancerProfileSummaryRequest, Long freelancerProfileId, String loginUsername) {
        ProfileDetail profileDetail = profileDetailJpaRepository.findById(freelancerProfileId)
                .orElseThrow(()-> new NotFoundException("Profile not found"));

        if(profileDetail.getSummary()!=null && profileDetail.getCvUrl()!=null){
            throw new BadRequestException("This profile already has a summary. You can try creating one by deleting the existing summary.");
        }

        User currentUser = userJpaRepository.findByLoginUsername(loginUsername)
                .orElseThrow(() -> new RuntimeException("User not found!"));

        if (!currentUser.getId().equals(profileDetail.getUser().getId())) {
            throw new RuntimeException("You are not authorized to create a summary for this profile.");
        }

        ProfileDetail withSummary = ProfileDetail.builder()
                .cvUrl(freelancerProfileSummaryRequest.getCvUrl())
                .summary(freelancerProfileSummaryRequest.getSummary())
                .build();

        ProfileDetail savedProfileDetail = profileDetailJpaRepository.save(withSummary);
        return profileDetailMapper.toFreelancerProfileSummaryResponse(savedProfileDetail);
    }

    @Override
    public List<FreelancerProfileSummaryResponse> retrieveAll() {
        List<ProfileDetail> summaryProfileDetails = profileDetailJpaRepository.findAll();
        return summaryProfileDetails.stream()
                .filter(summaryProfileDetail-> summaryProfileDetail.getUser().getRole().getName().equals("Freelancer") && summaryProfileDetail.getSummary()!=null && summaryProfileDetail.getCvUrl()!=null)
                .map(profileDetailMapper::toFreelancerProfileSummaryResponse)
                .toList();
    }

    @Override
    public FreelancerProfileSummaryResponse retrieveOne(Long id) {
        ProfileDetail profileDetail = profileDetailJpaRepository.findById(id)
                .orElseThrow(()-> new NotFoundException("Profile Detail Not found"));

        if(profileDetail.getSummary()==null && profileDetail.getCvUrl()==null){
            throw new BadRequestException("Summary not found");
        }
        return profileDetailMapper.toFreelancerProfileSummaryResponse(profileDetail);
    }

    @Override
    public FreelancerProfileSummaryResponse updateFreelancerSummary(Long id, FreelancerProfileSummaryRequest freelancerProfileSummaryRequest, String loginUsername) {
        User currentUser = userJpaRepository.findByLoginUsername(loginUsername)
                .orElseThrow(() -> new RuntimeException("User not found!"));

        ProfileDetail profileDetail = profileDetailJpaRepository.findById(id)
                .orElseThrow(()-> new NotFoundException("Profile Detail Not found"));

        if(profileDetail.getSummary()==null && profileDetail.getCvUrl()==null){
            throw new BadRequestException("Summary not found");
        }

        if (!currentUser.getId().equals(profileDetail.getUser().getId())) {
            throw new RuntimeException("You are not authorized to update this profile!");
        }

        profileDetail.setSummary(freelancerProfileSummaryRequest.getSummary());
        profileDetail.setCvUrl(freelancerProfileSummaryRequest.getSummary());

        ProfileDetail updatedProfileSummary = profileDetailJpaRepository.save(profileDetail);
        return profileDetailMapper.toFreelancerProfileSummaryResponse(updatedProfileSummary);
    }

    @Override
    public void deleteFreelancerProfileSummary(Long id) {
        ProfileDetail profileDetail = profileDetailJpaRepository.findById(id)
                .orElseThrow(()-> new NotFoundException("Summary not found!"));
        if(profileDetail.getSummary()==null && profileDetail.getCvUrl()==null){
                throw new BadRequestException("This summary has already been moved to recycle bin");
        }
        profileDetail.setSummary(null);
        profileDetail.setCvUrl(null);
        profileDetailJpaRepository.save(profileDetail);
    }

}
