package com.springboot.jobseeker.feature.coin.admin.service.impl;

import com.springboot.jobseeker.feature.coin.admin.dto.ManageCoinRequest;
import com.springboot.jobseeker.feature.coin.admin.service.ManageCoinService;
import com.springboot.jobseeker.feature.coin.dto.PurchaseHistoryResponse;
import com.springboot.jobseeker.feature.user.dto.UserResponse;
import com.springboot.jobseeker.feature.user.repository.jpa.UserJpaRepository;
import com.springboot.jobseeker.shared.data.enums.Status;
import com.springboot.jobseeker.shared.data.model.ProfileDetail;
import com.springboot.jobseeker.shared.data.model.PurchaseHistory;
import com.springboot.jobseeker.shared.data.model.User;
import com.springboot.jobseeker.shared.data.repository.jdbc.UserJdbcRepository;
import com.springboot.jobseeker.shared.data.repository.jpa.ProfileDetailJpaRepository;
import com.springboot.jobseeker.shared.data.repository.jpa.PurchaseHistoryJpaRepository;
import com.springboot.jobseeker.shared.exception.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@AllArgsConstructor
public class ManageCoinServiceImpl implements ManageCoinService {
    private final PurchaseHistoryJpaRepository purchaseHistoryJpaRepository;
    private final UserJpaRepository userJpaRepository;
    private final UserJdbcRepository userJdbcRepository;
    private final ProfileDetailJpaRepository profileDetailJpaRepository;

    @Override
    public PurchaseHistoryResponse manageCoin(Long purchaseHistoryId, ManageCoinRequest manageCoinRequest, String loginUsername) {
        PurchaseHistory requestedPurchase = purchaseHistoryJpaRepository.findById(purchaseHistoryId)
                .orElseThrow(()-> new NotFoundException("Purchase History not found"));

        if (Status.PENDING.getCode()!=requestedPurchase.getStatusId()){
            throw new RuntimeException("You can manage purchaseHistory with the status of PENDING only");
        }

        requestedPurchase.setStatusId(manageCoinRequest.statusId());
        requestedPurchase.setAdmin(userJpaRepository.findByLoginUsername(loginUsername).orElseThrow(()-> new NotFoundException("Admin not found")));

        PurchaseHistory updatedPurchaseHistory = purchaseHistoryJpaRepository.save(requestedPurchase);
        Status status = Status.fromCode(updatedPurchaseHistory.getStatusId());

        User user = userJpaRepository.findByLoginUsername(updatedPurchaseHistory.getUser().getLoginUsername()).orElseThrow(()-> new NotFoundException("User not found"));
        if(status.getDescription().equals("Accept")){
            ProfileDetail requestedUserProfile = profileDetailJpaRepository.findByUser(user).orElseThrow();
            requestedUserProfile.setTotalCoin(requestedUserProfile.getTotalCoin().add(updatedPurchaseHistory.getAmount()));
        }

        UserResponse freelancer = userJdbcRepository.findByLoginUsername(updatedPurchaseHistory.getUser().getLoginUsername());
        UserResponse admin = userJdbcRepository.findByLoginUsername(updatedPurchaseHistory.getAdmin().getLoginUsername());

        return PurchaseHistoryResponse.builder()
                .id(updatedPurchaseHistory.getId())
                .amount(updatedPurchaseHistory.getAmount())
                .freelancer(freelancer)
                .admin(admin)
                .statusValue(status)
                .build();
    }
}
