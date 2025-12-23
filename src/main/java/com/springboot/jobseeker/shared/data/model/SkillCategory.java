package com.springboot.jobseeker.shared.data.model;

import jakarta.persistence.*;

@Entity
@Table(name="skill_categories")
public class SkillCategory extends MasterEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
}
