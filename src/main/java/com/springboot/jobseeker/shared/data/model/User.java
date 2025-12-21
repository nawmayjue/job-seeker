package com.springboot.jobseeker.shared.data.model;

import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class User extends MasterEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fullName;
    private String loginUsername;
    private String loginEmail;
    private String password;

    @ManyToOne(fetch = FetchType.LAZY)
    private Role role;
}
