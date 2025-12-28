package com.springboot.jobseeker.feature.profiledetails.controller;

import com.springboot.jobseeker.feature.profiledetails.dto.FreelancerProfileSummaryRequest;
import com.springboot.jobseeker.feature.profiledetails.service.FreelancerProfileSummaryService;
import com.springboot.jobseeker.shared.data.dto.ApiResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/job-seeker/profile/summary/freelancer")
@AllArgsConstructor
public class FreelancerProfileSummaryController {
    private final FreelancerProfileSummaryService profileSummaryService;

    @PostMapping
    private ResponseEntity<?> createFreelancerProfile(
            @RequestBody FreelancerProfileSummaryRequest freelancerProfileSummaryRequest,
            @PathVariable Long freelancerProfileId
    ){
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String username = authentication.getName();

            return ResponseEntity.ok(
                    ApiResponse.builder()
                            .status(201)
                            .data(profileSummaryService.createFreelancerProfileSummary(freelancerProfileSummaryRequest, freelancerProfileId, username))
                            .message("Successfully added a summary to your profile")
                            .build()
            );
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping
    private ResponseEntity<ApiResponse> getSummaryProfile(){
        return ResponseEntity.ok(
                ApiResponse.builder()
                        .status(200)
                        .data(profileSummaryService.retrieveAll())
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
                            .data(profileSummaryService.retrieveOne(id))
                            .message("Freelancer Profile Retrieved Successfully.")
                            .build());
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    private ResponseEntity<?> updateFreelancerProfile(@PathVariable Long id, @RequestBody FreelancerProfileSummaryRequest freelancerProfileSummaryRequest){
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String username = authentication.getName();

            return ResponseEntity.ok(
                    ApiResponse.builder()
                            .status(200)
                            .data(profileSummaryService.updateFreelancerSummary(id, freelancerProfileSummaryRequest, username))
                            .message("Freelancer Profile Summary Updated Successfully.")
                            .build());
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    private ResponseEntity<?> deleteFreelancerProfile(@PathVariable Long id){
        try {
            profileSummaryService.deleteFreelancerProfileSummary(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
