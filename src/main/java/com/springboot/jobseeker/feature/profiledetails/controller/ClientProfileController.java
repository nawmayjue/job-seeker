package com.springboot.jobseeker.feature.profiledetails.controller;

import com.springboot.jobseeker.feature.profiledetails.dto.ClientProfileRequest;
import com.springboot.jobseeker.feature.profiledetails.dto.ClientProfileResponse;
import com.springboot.jobseeker.feature.profiledetails.service.ClientProfileService;
import com.springboot.jobseeker.feature.skillcategory.dto.UpdateSkillCategoryRequest;
import com.springboot.jobseeker.shared.data.dto.ApiResponse;
import com.springboot.jobseeker.shared.exception.BadRequestException;
import com.springboot.jobseeker.shared.exception.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/job-seeker/profile/client")
@AllArgsConstructor
public class ClientProfileController {
    private final ClientProfileService clientProfileService;

    @PostMapping
    private ResponseEntity<ApiResponse> createClientProfile(
            @RequestBody ClientProfileRequest clientProfileRequest
            ){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        ClientProfileResponse clientProfileResponse= clientProfileService.createClientProfile(clientProfileRequest, username);
        return ResponseEntity.ok(
                ApiResponse.builder()
                        .status(HttpStatus.OK.value())
                        .data(clientProfileResponse)
                        .message("Successfully added your profile")
                        .build()
        );
    }

    @GetMapping
    public ResponseEntity<ApiResponse> retrieveAllUsers(){
        return ResponseEntity.ok(
                new ApiResponse(
                        200,
                        clientProfileService.retrieveAll(),
                        "Users retrieved successfully"
                )
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse> retrieveUserById(
            @PathVariable Long id
    ){
        return ResponseEntity.ok(
                new ApiResponse(
                        200,
                        clientProfileService.retrieveOne(id),
                        "User retrieved successfully"
                )
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteById(
            @PathVariable Long id
    ){
        try {
            clientProfileService.deleteClientById(id);
            return ResponseEntity.noContent().build();
        }catch (NotFoundException e) {
            return ResponseEntity.ok()
                    .body(
                            ApiResponse.builder()
                                    .status(HttpStatus.NOT_FOUND.value())
                                    .data(false)
                                    .message(e.getMessage())
                                    .build()
                    );
        } catch (BadRequestException be) {
            return ResponseEntity.ok()
                    .body(
                            ApiResponse.builder()
                                    .status(HttpStatus.BAD_REQUEST.value())
                                    .data(false)
                                    .message(be.getMessage())
                                    .build()
                    );
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> updateClientProfile(
            @PathVariable Long id,
            @RequestBody ClientProfileRequest request
    ){
        ApiResponse apiResponse;
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String loginUsername = authentication.getName();
        try{apiResponse = ApiResponse.builder()
                .status(201)
                .data(
                        clientProfileService.updateClientProfile(request, id, loginUsername)
                )
                .message("Client Profile Updated Successfully.")
                .build();
        } catch (BadRequestException e) {
            apiResponse = ApiResponse.builder()
                    .status(HttpStatus.BAD_REQUEST.value())
                    .data(false)
                    .message(e.getMessage())
                    .build();
        }
        return ResponseEntity.ok().body(
                apiResponse
        );
    }

}
