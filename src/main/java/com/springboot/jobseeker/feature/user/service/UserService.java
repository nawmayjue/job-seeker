package com.springboot.jobseeker.feature.user.service;

import com.springboot.jobseeker.feature.user.dto.UserResponse;

import java.util.List;

public interface UserService {
    UserResponse retrieveUserById(Long id);
    List<UserResponse> retrieveAllUsers();
    void deleteUserById(Long id);
}
