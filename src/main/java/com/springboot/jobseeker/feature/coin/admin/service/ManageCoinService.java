package com.springboot.jobseeker.feature.coin.admin.service;

import com.springboot.jobseeker.feature.coin.admin.dto.ManageCoinRequest;
import com.springboot.jobseeker.feature.coin.dto.PurchaseHistoryResponse;

public interface ManageCoinService {
    PurchaseHistoryResponse manageCoin(Long purchaseHistoryId, ManageCoinRequest manageCoinRequest, String loginUsername);
}
