package com.springboot.jobseeker.shared.data.mapper;

import com.springboot.jobseeker.feature.user.dto.UserResponse;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRowMapper implements RowMapper<UserResponse> {
    @Override
    public UserResponse mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new UserResponse(
                rs.getLong("id"),
                rs.getString("fullName"),
                rs.getString("loginUsername"),
                rs.getString("loginEmail"),
                null
        );
    }
}
