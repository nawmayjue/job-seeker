package com.springboot.jobseeker.shared.data.repository.jdbc;


import com.springboot.jobseeker.feature.user.dto.UserResponse;

import java.util.List;

public interface UserJdbcRepository {
    public List<UserResponse> findAll();
    public UserResponse findByLoginUsername(String loginUsername);
}
