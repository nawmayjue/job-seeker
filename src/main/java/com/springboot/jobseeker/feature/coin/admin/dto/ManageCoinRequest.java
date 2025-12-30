package com.springboot.jobseeker.feature.coin.admin.dto;

import lombok.Builder;

@Builder
public record ManageCoinRequest (
        Integer statusId
){}
