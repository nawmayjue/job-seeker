package com.springboot.jobseeker.feature.auth.dto;

import com.springboot.jobseeker.feature.user.dto.UserResponse;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoginResponse {
    private String token;
//    private UserResponse user; This is homework

}
