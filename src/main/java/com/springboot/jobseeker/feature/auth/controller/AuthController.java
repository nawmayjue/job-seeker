package com.springboot.jobseeker.feature.auth.controller;

import com.springboot.jobseeker.feature.auth.dto.LoginResponse;
import com.springboot.jobseeker.feature.user.dto.AuthRequest;
import com.springboot.jobseeker.feature.user.dto.UserRegisterRequest;
import com.springboot.jobseeker.feature.user.service.JwtService;
import com.springboot.jobseeker.feature.user.service.impl.UserServiceImpl;
import com.springboot.jobseeker.shared.data.dto.ApiResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/job-seeker/auth")
@AllArgsConstructor
public class AuthController {
    private final UserServiceImpl service;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    @PostMapping("/register")
    public ResponseEntity<ApiResponse> addNewUser(
            @RequestBody UserRegisterRequest userInfo) {
        String response = service.addUser(userInfo);
        return ResponseEntity.status(HttpStatus.CREATED).body(
                new ApiResponse(201, true, response)
        );
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse> authenticateAndGetToken(@RequestBody AuthRequest authRequest) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getLoginUsername(), authRequest.getPassword()));
        if (authentication.isAuthenticated()) {
            String token = jwtService.generateToken(authRequest.getLoginUsername());
            return ResponseEntity.ok(
                    ApiResponse.builder()
                            .status(200)
                            .data(new LoginResponse(token))
                            .message("Login successful!")
                            .build()
            );
        } else {
            throw new UsernameNotFoundException("Invalid user request!");
        }
    }
}
