package com.springboot.jobseeker.shared.data.repository.jdbc;

import com.springboot.jobseeker.feature.role.dto.RoleResponse;

import java.util.List;

public interface RoleJdbcRepository {
    List<RoleResponse> findAll();
    RoleResponse findById(Long id);
}
