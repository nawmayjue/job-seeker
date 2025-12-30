package com.springboot.jobseeker.shared.data.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name="profile_details")
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProfileDetail extends MasterEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String education;
    private String address;
    private String summary;
    private String cvUrl;
    private BigDecimal totalCoin;
    private String companyName;

    @ManyToOne(fetch = FetchType.LAZY)
    private Country country;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

}
