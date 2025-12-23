package com.springboot.jobseeker.shared.data.model;

import jakarta.persistence.*;

@Entity
@Table(name="users_skill_categories")
public class UserSkillCategory extends MasterEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    private SkillCategory skillCategory;
}
