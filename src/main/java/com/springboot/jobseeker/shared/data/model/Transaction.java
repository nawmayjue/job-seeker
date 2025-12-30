package com.springboot.jobseeker.shared.data.model;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name="transactions")
public class Transaction extends MasterEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private BigDecimal amount;

    @ManyToOne(fetch = FetchType.LAZY)
    private User freelancer;

    @ManyToOne(fetch = FetchType.LAZY)
    private JobPost jobPost;
}
