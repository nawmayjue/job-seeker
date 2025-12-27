package com.springboot.jobseeker.feature.profiledetails.service;

import com.springboot.jobseeker.feature.profiledetails.dto.ClientProfileRequest;
import com.springboot.jobseeker.feature.profiledetails.dto.ClientProfileResponse;

import java.util.List;

public interface ClientProfileService {
    ClientProfileResponse createClientProfile(ClientProfileRequest createClientProfileRequest, String loginUsername);
    ClientProfileResponse retrieveOne(Long id);
    List<ClientProfileResponse> retrieveAll();
    ClientProfileResponse updateClientProfile(ClientProfileRequest updateClientProfileRequest, Long id, String loginUsername);
    void deleteClientById(Long id);
}
