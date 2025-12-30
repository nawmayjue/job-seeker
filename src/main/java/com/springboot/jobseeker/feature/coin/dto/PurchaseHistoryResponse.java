package com.springboot.jobseeker.feature.coin.dto;

import com.springboot.jobseeker.feature.user.dto.UserResponse;
import com.springboot.jobseeker.shared.data.enums.Status;
import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record PurchaseHistoryResponse(
    Long id,
    BigDecimal amount,
    UserResponse freelancer,
    UserResponse admin,
    Status statusValue
) {}
