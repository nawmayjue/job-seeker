package com.springboot.jobseeker.feature.profiledetails.service.impl;

import com.springboot.jobseeker.feature.country.dto.CountriesResponse;
import com.springboot.jobseeker.feature.profiledetails.dto.ClientProfileRequest;
import com.springboot.jobseeker.feature.profiledetails.dto.ClientProfileResponse;
import com.springboot.jobseeker.feature.profiledetails.service.ClientProfileService;
import com.springboot.jobseeker.feature.user.dto.UserResponse;
import com.springboot.jobseeker.feature.user.repository.jpa.UserJpaRepository;
import com.springboot.jobseeker.shared.data.model.Country;
import com.springboot.jobseeker.shared.data.model.ProfileDetail;
import com.springboot.jobseeker.shared.data.model.User;
import com.springboot.jobseeker.shared.data.repository.jdbc.UserJdbcRepository;
import com.springboot.jobseeker.shared.data.repository.jpa.ClientProfileJpaRepository;
import com.springboot.jobseeker.shared.data.repository.jpa.CountryJpaRepository;
import com.springboot.jobseeker.shared.exception.BadRequestException;
import com.springboot.jobseeker.shared.exception.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.Arrays.stream;

@Service
@AllArgsConstructor
public class ClientProfileServiceImpl implements ClientProfileService {
    private final CountryJpaRepository countryJpaRepository;
    private final ClientProfileJpaRepository clientProfileJpaRepository;
    private final UserJdbcRepository userJdbcRepository;
    private final UserJpaRepository userJpaRepository;

    @Override
    public ClientProfileResponse createClientProfile(ClientProfileRequest createClientProfileRequest, String loginUsername) {

        if(!countryJpaRepository.existsById(createClientProfileRequest.getCountryId())){
            throw new NotFoundException("Country with" + createClientProfileRequest.getCountryId() + " not found");
        }

        User user= userJpaRepository.findByLoginUsername(loginUsername)
                .orElseThrow(()-> new NotFoundException("Not found"));

        Country country = countryJpaRepository.findById(createClientProfileRequest.getCountryId())
                .orElseThrow(()-> new NotFoundException("Country with" + createClientProfileRequest.getCountryId() + " not found"));

        ProfileDetail clientProfile = ProfileDetail.builder()
                .address(createClientProfileRequest.getAddress())
                .companyName(createClientProfileRequest.getCompanyName())
                .country(country)
                .user(user)
                .build();

        ProfileDetail savedClientProfile = clientProfileJpaRepository.save(clientProfile);

       UserResponse userResponse = userJdbcRepository.findByLoginUsername(loginUsername);

               CountriesResponse firstChosenCountry = CountriesResponse.builder()
                       .id(country.getId())
                       .name(country.getName())
                       .createdAt(country.getCreatedAt())
                       .updatedAt(country.getUpdatedAt())
                       .build();
//               countryJpaRepository.findById(createClientProfileRequest.getCountryId())
//                       .map(chosenCountry-> CountriesResponse.builder()
//                                   .id(chosenCountry.getId())
//                                   .name(chosenCountry.getName())
//                                   .createdAt(savedClientProfile.getCountry().getCreatedAt())
//                                   .updatedAt(savedClientProfile.getCountry().getUpdatedAt())
//                                   .build()
//                           )
//                       .orElse(null);

       return ClientProfileResponse.builder()
               .id(savedClientProfile.getId())
               .userResponse(userResponse)
               .address(savedClientProfile.getAddress())
               .companyName(savedClientProfile.getCompanyName())
               .countryResponse(firstChosenCountry)
               .build();
    }

    @Override
    public ClientProfileResponse retrieveOne(Long id) {
        ProfileDetail profileDetail = clientProfileJpaRepository.findById(id)
                .orElseThrow(()->new NotFoundException("Not found"));

        if(profileDetail.getDeletedBy()!=null){
            if (profileDetail.getDeletedAt()!=null){
                throw new BadRequestException("This profile has been moved to recycle bin");
            }
        }

        Country country = countryJpaRepository.findById(profileDetail.getCountry().getId())
                .orElseThrow(()-> new NotFoundException("Country with" + profileDetail.getCountry().getId()+ " not found"));

        User user= userJpaRepository.findById(profileDetail.getUser().getId())
                .orElseThrow(()-> new NotFoundException("Not found"));

        CountriesResponse firstChosenCountry = CountriesResponse.builder()
                .id(country.getId())
                .name(country.getName())
                .createdAt(country.getCreatedAt())
                .updatedAt(country.getUpdatedAt())
                .build();

        return ClientProfileResponse.builder()
                .id(profileDetail.getId())
                .address(profileDetail.getAddress())
                .companyName(profileDetail.getCompanyName())
                .countryResponse(firstChosenCountry)
                .userResponse(
                      UserResponse.builder()
                              .id(user.getId())
                              .fullName(user.getFullName())
                              .build()
                )
                .build();
    }

    @Override
    public List<ClientProfileResponse> retrieveAll() {
        List<ProfileDetail> profileDetails = clientProfileJpaRepository.findAll();

        return profileDetails.stream()
                .filter(profileDetail -> profileDetail.getUser().getRole().getName().equals("Client") && profileDetail.getDeletedAt() == null &&
                        profileDetail.getDeletedBy() == null)
                .map(
                        profileDetail -> ClientProfileResponse.builder().id(profileDetail.getId())
                                .address(profileDetail.getAddress())
                                .companyName(profileDetail.getCompanyName())
                                .countryResponse(null)
                                .build()
        ).toList();
    }

    @Override
    public ClientProfileResponse updateClientProfile(ClientProfileRequest updateClientProfileRequest, Long id, String loginUsername) {
        ProfileDetail profileDetail = clientProfileJpaRepository.findById(id)
                .orElseThrow(()-> new NotFoundException("Profile not found!"));

        if(profileDetail.getDeletedBy()!=null && profileDetail.getDeletedAt()!=null){
                throw new BadRequestException("This profile has been moved to recycle bin");
        }

        Country country = countryJpaRepository.findById(updateClientProfileRequest.getCountryId())
                .orElseThrow(()-> new NotFoundException("Country with" + updateClientProfileRequest.getCountryId() + " not found"));

        ProfileDetail updatedProfile = ProfileDetail.builder()
                .id(id)
                .address(updateClientProfileRequest.getAddress())
                .companyName(updateClientProfileRequest.getCompanyName())
                .country(country)
                .build();

        ProfileDetail updatedProfileDetail = clientProfileJpaRepository.save(updatedProfile);

        UserResponse userResponse = userJdbcRepository.findByLoginUsername(loginUsername);

        CountriesResponse countryResponse = CountriesResponse.builder()
                .id(updatedProfileDetail.getCountry().getId())
                .name(updatedProfileDetail.getCountry().getName())
                .createdAt(updatedProfileDetail.getCountry().getCreatedAt())
                .updatedAt(updatedProfileDetail.getCountry().getUpdatedAt())
                .build();

        return ClientProfileResponse.builder()
                .id(updatedProfileDetail.getId())
                .userResponse(userResponse)
                .address(updatedProfileDetail.getAddress())
                .companyName(updatedProfileDetail.getCompanyName())
                .countryResponse(countryResponse)
                .build();

    }

    @Override
    public void deleteClientById(Long id) {
        ProfileDetail profileDetail = clientProfileJpaRepository.findById(id)
                .orElseThrow(()->new NotFoundException("Not found"));

        if(profileDetail.getDeletedBy()!=null && profileDetail.getDeletedAt()!=null){
            throw new BadRequestException("This profile has been moved to recycle bin");
        }
        profileDetail.delete(1L);
        clientProfileJpaRepository.save(profileDetail);
    }
}
