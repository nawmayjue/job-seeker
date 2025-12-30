package com.springboot.jobseeker.feature.coin.freelancer.dto;

import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record PurchaseCoinRequest (
        BigDecimal amount
){}
