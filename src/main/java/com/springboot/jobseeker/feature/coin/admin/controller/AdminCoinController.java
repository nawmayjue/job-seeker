package com.springboot.jobseeker.feature.coin.admin.controller;

import com.springboot.jobseeker.feature.coin.admin.dto.ManageCoinRequest;
import com.springboot.jobseeker.feature.coin.admin.service.ManageCoinService;
import com.springboot.jobseeker.shared.data.dto.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/v1/job-seeker/admin/coin")
@RestController
@AllArgsConstructor
@Tag(name="Admin Coin")
public class AdminCoinController {
    private final ManageCoinService manageCoinService;

    @PatchMapping("/{purchaseHistoryId}/manage-status")
    public ResponseEntity<?> manageStatus(
            @PathVariable Long purchaseHistoryId,
            @RequestBody ManageCoinRequest manageCoinRequest
            ){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        return ResponseEntity.ok(
                ApiResponse.builder()
                        .status(HttpStatus.OK.value())
                        .data(manageCoinService.manageCoin(purchaseHistoryId, manageCoinRequest, username))
                        .message("Change status successfully")
                        .build()
        );
    }
}
