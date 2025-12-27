package com.springboot.jobseeker.shared.data.repository.jdbc.impl;

import com.springboot.jobseeker.feature.user.dto.UserResponse;
import com.springboot.jobseeker.shared.data.mapper.UserRowMapper;
import com.springboot.jobseeker.shared.data.repository.jdbc.UserJdbcRepository;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@AllArgsConstructor
@Repository
public class UserJdbcRepositoryImpl implements UserJdbcRepository {
    private final static UserRowMapper USER_ROW_MAPPER= new UserRowMapper();
    private final JdbcTemplate jdbctemplate;

    private static final String FIND_ALL_QUERY= """
            SELECT id,full_name AS fullName,login_username AS loginUsername,login_email AS loginEmail FROM users
            """;
    private static final String FIND_BY_LOGIN_USERNAME_QUERY= """
            SELECT id,full_name AS fullName,login_username AS loginUsername,login_email AS loginEmail FROM users WHERE login_username=?
            """;

    @Override
    public List<UserResponse> findAll() {
        return this.jdbctemplate.query(
                FIND_ALL_QUERY,
                USER_ROW_MAPPER
        );
    }

    @Override
    public UserResponse findByLoginUsername(String loginUsername) {
        return this.jdbctemplate.queryForObject(
                FIND_BY_LOGIN_USERNAME_QUERY,
                USER_ROW_MAPPER,
                loginUsername
        );
    }
}
