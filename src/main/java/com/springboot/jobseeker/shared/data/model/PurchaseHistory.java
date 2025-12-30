package com.springboot.jobseeker.shared.data.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name="purchase_history")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PurchaseHistory extends MasterEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private BigDecimal amount;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    private User admin;

    @Column(name="status_id")
    private Integer statusId;
}
