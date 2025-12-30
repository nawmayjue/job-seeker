package com.springboot.jobseeker.shared.data.mapper;

import com.springboot.jobseeker.feature.role.dto.RoleResponse;
import com.springboot.jobseeker.feature.user.dto.UserResponse;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRowMapper implements RowMapper<UserResponse> {
    @Override
    public UserResponse mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new UserResponse(
                rs.getLong("userId"),
                rs.getString("userFullName"),
                rs.getString("userLoginUsername"),
                rs.getString("userLoginEmail"),
                new RoleResponse(
                        rs.getLong("roleId"),
                        rs.getString("roleName")
                )
        );
    }
}
