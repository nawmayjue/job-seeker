package com.springboot.jobseeker.feature.coin.freelancer.service;

import com.springboot.jobseeker.feature.coin.freelancer.dto.PurchaseCoinRequest;

public interface FreelancerCoinService {
    Long purchaseCoin(PurchaseCoinRequest purchaseCoinRequest, String loginUsername);
}
