package com.springboot.jobseeker.shared.data.repository.jpa;

import com.springboot.jobseeker.shared.data.model.SkillCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SkillCategoryJpaRepository extends JpaRepository<SkillCategory, Long> {
    boolean existsByName(String name);
    SkillCategory findByName(String name);
}
