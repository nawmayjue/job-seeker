package com.springboot.jobseeker.feature.profiledetails.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FreelancerProfileSummaryRequest {
    private String cvUrl;
    private String summary;
}
