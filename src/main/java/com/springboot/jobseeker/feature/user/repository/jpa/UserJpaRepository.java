package com.springboot.jobseeker.feature.user.repository.jpa;

import com.springboot.jobseeker.shared.data.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserJpaRepository extends JpaRepository<User, Long> {
    Optional<User> findByLoginUsername(String loginUsername);
}
