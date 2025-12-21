package com.springboot.jobseeker.shared.data.model;

import jakarta.persistence.*;

@Entity
@Table(name="profile_details")
public class ProfileDetail extends MasterEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String education;
    private String address;
    private String summary;
    private String cvUrl;
    private Integer totalCoin;
    private String companyName;

    @ManyToOne(fetch = FetchType.LAZY)
    private Country country;

}
