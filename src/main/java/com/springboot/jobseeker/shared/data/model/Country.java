package com.springboot.jobseeker.shared.data.model;

import jakarta.persistence.*;

@Entity
@Table(name="countries")
public class Country extends MasterEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
}
