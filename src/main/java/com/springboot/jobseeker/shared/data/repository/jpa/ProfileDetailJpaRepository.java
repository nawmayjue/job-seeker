package com.springboot.jobseeker.shared.data.repository.jpa;

import com.springboot.jobseeker.shared.data.model.ProfileDetail;
import com.springboot.jobseeker.shared.data.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProfileDetailJpaRepository extends JpaRepository<ProfileDetail, Long> {
    Optional<ProfileDetail> findByUser(User user);
}
