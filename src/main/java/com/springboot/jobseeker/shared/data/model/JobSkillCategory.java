package com.springboot.jobseeker.shared.data.model;

import jakarta.persistence.*;

@Entity
@Table(name="jobs_skill_categories")
public class JobSkillCategory extends MasterEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private JobPost jobPost;

    @ManyToOne(fetch = FetchType.LAZY)
    private SkillCategory skillCategory;
}
