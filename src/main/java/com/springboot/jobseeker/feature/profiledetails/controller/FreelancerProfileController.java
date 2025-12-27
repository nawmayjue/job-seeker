package com.springboot.jobseeker.feature.profiledetails.controller;

import com.springboot.jobseeker.feature.profiledetails.dto.FreelancerProfileRequest;
import com.springboot.jobseeker.feature.profiledetails.service.FreelancerProfileService;
import com.springboot.jobseeker.shared.data.dto.ApiResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/job-seeker/profile/free-lancer")
@AllArgsConstructor
public class FreelancerProfileController {

    private final FreelancerProfileService freelancerProfileService;

    @PostMapping
    private ResponseEntity<?> createFreelancerProfile(
            @RequestBody FreelancerProfileRequest freelancerProfileRequest
    ){
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String username = authentication.getName();

            return ResponseEntity.ok(
                    ApiResponse.builder()
                            .status(201)
                            .data(freelancerProfileService.createFreelancerProfile(freelancerProfileRequest, username))
                            .message("Successfully added your profile")
                            .build()
            );
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping
    private ResponseEntity<ApiResponse> getFreelancerProfile(){
        return ResponseEntity.ok(
                ApiResponse.builder()
                        .status(200)
                        .data(freelancerProfileService.retrieveAll())
                        .message("Freelancer Profiles Retrieved Successfully.")
                        .build()
        );
    }

    @GetMapping("/{id}")
    private ResponseEntity<?> getFreelancerProfileById(@PathVariable Long id){
        try {
            return ResponseEntity.ok(
                    ApiResponse.builder()
                            .status(200)
                            .data(freelancerProfileService.retrieveOne(id))
                            .message("Freelancer Profile Retrieved Successfully.")
                            .build());
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    private ResponseEntity<?> updateFreelancerProfile(@PathVariable Long id, @RequestBody FreelancerProfileRequest freelancerProfileRequest){
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String username = authentication.getName();

            return ResponseEntity.ok(
                    ApiResponse.builder()
                            .status(200)
                            .data(freelancerProfileService.updateFreelancerProfile(freelancerProfileRequest, id, username))
                            .message("Freelancer Profile Updated Successfully.")
                            .build());
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    private ResponseEntity<?> deleteFreelancerProfile(@PathVariable Long id){
        try {
            freelancerProfileService.deleteFreelancerById(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
