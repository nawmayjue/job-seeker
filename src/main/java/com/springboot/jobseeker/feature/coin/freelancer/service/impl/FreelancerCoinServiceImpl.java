package com.springboot.jobseeker.feature.coin.freelancer.service.impl;

import com.springboot.jobseeker.feature.coin.freelancer.dto.PurchaseCoinRequest;
import com.springboot.jobseeker.feature.coin.freelancer.service.FreelancerCoinService;
import com.springboot.jobseeker.feature.user.repository.jpa.UserJpaRepository;
import com.springboot.jobseeker.shared.data.enums.Status;
import com.springboot.jobseeker.shared.data.model.PurchaseHistory;
import com.springboot.jobseeker.shared.data.model.User;
import com.springboot.jobseeker.shared.data.repository.jpa.PurchaseHistoryJpaRepository;
import com.springboot.jobseeker.shared.exception.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class FreelancerCoinServiceImpl implements FreelancerCoinService {
    private final UserJpaRepository userJpaRepository;
    private final PurchaseHistoryJpaRepository purchaseHistoryJpaRepository;

    @Override
    public Long purchaseCoin(PurchaseCoinRequest purchaseCoinRequest, String loginUsername) {
        User user = userJpaRepository.findByLoginUsername(loginUsername)
                .orElseThrow(()-> new NotFoundException("User Not found"));
        PurchaseHistory purchaseHistory = PurchaseHistory.builder()
                .user(user)
                .amount(purchaseCoinRequest.amount())
                .statusId(Status.PENDING.getCode())
                .build();
        return purchaseHistoryJpaRepository.save(purchaseHistory).getId();
    }
}
