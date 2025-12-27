package com.springboot.jobseeker.shared.data.model;


import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name="job_posts")
public class JobPost extends MasterEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;
    private BigDecimal fromSalary;
    private BigDecimal toSalary;

    @ManyToOne(fetch = FetchType.LAZY)
    private User owner;
}
