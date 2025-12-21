package com.springboot.jobseeker.feature.role.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RoleResponse {
    private Long id;
    private String name;
}
