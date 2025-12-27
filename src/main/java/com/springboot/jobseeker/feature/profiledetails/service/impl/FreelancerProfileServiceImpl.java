package com.springboot.jobseeker.feature.profiledetails.service.impl;

import com.springboot.jobseeker.feature.profiledetails.dto.FreelancerProfileRequest;
import com.springboot.jobseeker.feature.profiledetails.dto.FreelancerProfileResponse;
import com.springboot.jobseeker.feature.profiledetails.mapper.ProfileDetailMapper;
import com.springboot.jobseeker.feature.profiledetails.service.FreelancerProfileService;
import com.springboot.jobseeker.feature.user.repository.jpa.UserJpaRepository;
import com.springboot.jobseeker.shared.data.model.Country;
import com.springboot.jobseeker.shared.data.model.ProfileDetail;
import com.springboot.jobseeker.shared.data.model.User;
import com.springboot.jobseeker.shared.data.repository.jpa.CountryJpaRepository;
import com.springboot.jobseeker.shared.data.repository.jpa.FreelancerProfileJpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FreelancerProfileServiceImpl implements FreelancerProfileService {

    private final UserJpaRepository userJpaRepository;
    private final CountryJpaRepository countryJpaRepository;
    private final FreelancerProfileJpaRepository freelancerProfileJpaRepository;
    private final ProfileDetailMapper profileDetailMapper;

    public FreelancerProfileServiceImpl(UserJpaRepository userJpaRepository, CountryJpaRepository countryJpaRepository, FreelancerProfileJpaRepository freelancerProfileJpaRepository, ProfileDetailMapper profileDetailMapper) {
        this.userJpaRepository = userJpaRepository;
        this.countryJpaRepository = countryJpaRepository;
        this.freelancerProfileJpaRepository = freelancerProfileJpaRepository;
        this.profileDetailMapper = profileDetailMapper;
    }

    @Override
    public FreelancerProfileResponse createFreelancerProfile(FreelancerProfileRequest createFreelancerProfileRequest, String loginUsername) {
        User user = userJpaRepository.findByLoginUsername(loginUsername)
                .orElseThrow(() -> new RuntimeException("User not found!"));
        Country country = countryJpaRepository.findById(createFreelancerProfileRequest.getCountryId())
                .orElseThrow(() -> new RuntimeException("Country not found!"));

        ProfileDetail profileDetail = ProfileDetail.builder()
                .education(createFreelancerProfileRequest.getEducation())
                .address(createFreelancerProfileRequest.getAddress())
                .country(country)
                .user(user)
                .build();

        ProfileDetail savedProfileDetail = freelancerProfileJpaRepository.save(profileDetail);

        return profileDetailMapper.toFreelancerProfileResponse(savedProfileDetail);
    }

    @Override
    public FreelancerProfileResponse retrieveOne(Long id) {
        ProfileDetail profileDetail = freelancerProfileJpaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Profile not found!"));
        return profileDetailMapper.toFreelancerProfileResponse(profileDetail);
    }

    @Override
    public List<FreelancerProfileResponse> retrieveAll() {
        List<ProfileDetail> profileDetails = freelancerProfileJpaRepository.findAll();
        return profileDetails.stream()
                .filter(profileDetail -> profileDetail.getUser().getRole().getName().equals("Freelancer"))
                .map(profileDetailMapper::toFreelancerProfileResponse)
                .toList();
    }

    @Override
    public FreelancerProfileResponse updateFreelancerProfile(FreelancerProfileRequest updateFreelancerProfileRequest, Long id, String loginUsername) {
        User currentUser = userJpaRepository.findByLoginUsername(loginUsername)
                .orElseThrow(() -> new RuntimeException("User not found!"));

        Country country = countryJpaRepository.findById(updateFreelancerProfileRequest.getCountryId())
                .orElseThrow(() -> new RuntimeException("Country not found!"));
        ProfileDetail profileDetail = freelancerProfileJpaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Profile not found!"));

        if (!currentUser.getId().equals(profileDetail.getUser().getId())) {
            throw new RuntimeException("You are not authorized to update this profile!");
        }

        profileDetail.setEducation(updateFreelancerProfileRequest.getEducation());
        profileDetail.setAddress(updateFreelancerProfileRequest.getAddress());
        profileDetail.setCountry(country);

        ProfileDetail updatedProfileDetail = freelancerProfileJpaRepository.save(profileDetail);
        return profileDetailMapper.toFreelancerProfileResponse(updatedProfileDetail);
    }

    @Override
    public void deleteFreelancerById(Long id) {
        if(!freelancerProfileJpaRepository.existsById(id)){
            throw new RuntimeException("Profile not found!");
        }
        freelancerProfileJpaRepository.deleteById(id);
    }
}
