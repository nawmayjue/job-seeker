package com.springboot.jobseeker.feature.role.repository.jpa;

import com.springboot.jobseeker.shared.data.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleJpaRepository extends JpaRepository<Role, Long> {
}
