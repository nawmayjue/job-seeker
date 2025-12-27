package com.springboot.jobseeker.shared.data.repository.jpa;

import com.springboot.jobseeker.shared.data.model.ProfileDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FreelancerProfileJpaRepository extends JpaRepository<ProfileDetail, Long> {
    List<ProfileDetail> findAll();
}
