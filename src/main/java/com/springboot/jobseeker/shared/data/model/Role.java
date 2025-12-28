package com.springboot.jobseeker.shared.data.model;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Table(name="roles")
@Getter
public class Role extends MasterEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
}
