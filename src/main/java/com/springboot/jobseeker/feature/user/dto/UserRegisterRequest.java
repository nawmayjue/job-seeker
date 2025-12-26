package com.springboot.jobseeker.feature.user.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class UserRegisterRequest {
    @NotBlank
    @NotNull
//    @Max(value = 15, message = "display name cannot exceed 15")
    private String fullName;

    @NotBlank
    @NotNull
//    @Min(value = 5, message = "username must be at least 5")
//    @Max(value = 15, message = "username cannot exceed 15")
    private String loginUsername;

    //    @Email(message = "Invalid Email format")
    @NotBlank
    @NotNull
    private String loginEmail;

    @NotBlank
    @NotNull
//    @Min(value = 8, message = "password must be at least 5")
//    @Max(value = 15, message = "password cannot exceed 15")
    private String password;

//    @Min(value = 8, message = "password must be at least 5")
//    @Max(value = 15, message = "password cannot exceed 15")
    private Long roleId;
}
