package com.springboot.jobseeker.shared.data.model;

import jakarta.persistence.*;
import lombok.*;

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
    private Integer totalCoin;
    private String companyName;

    @ManyToOne(fetch = FetchType.LAZY)
    private Country country;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

}
