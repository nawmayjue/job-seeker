package com.springboot.jobseeker.feature.user.dto;

import com.springboot.jobseeker.feature.role.dto.RoleResponse;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class UserResponse {
    private Long id;
    private String fullName;
    private String loginUsername;
    private String loginEmail;
    private RoleResponse roleResponse;
}
