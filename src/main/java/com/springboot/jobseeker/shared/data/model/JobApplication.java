package com.springboot.jobseeker.shared.data.model;

import jakarta.persistence.*;

@Entity
@Table(name="job_applications")
public class JobApplication {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private JobPost jobPost;

    @ManyToOne(fetch = FetchType.LAZY)
    private User freelancer;

    @Column(name="status_id")
    private Integer statusId;
}
