package com.springboot.jobseeker.shared.data.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="countries")
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Country extends MasterEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
}
