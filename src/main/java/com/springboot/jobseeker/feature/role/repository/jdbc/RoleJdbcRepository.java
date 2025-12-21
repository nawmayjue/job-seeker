package com.springboot.jobseeker.feature.role.repository.jdbc;

import com.springboot.jobseeker.feature.role.dto.RoleResponse;

import java.util.List;

public interface RoleJdbcRepository {
    List<RoleResponse> findAll();
    RoleResponse findById(Long id);
}
