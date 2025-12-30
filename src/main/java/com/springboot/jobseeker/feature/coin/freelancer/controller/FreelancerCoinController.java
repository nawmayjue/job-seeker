package com.springboot.jobseeker.feature.coin.freelancer.controller;

import com.springboot.jobseeker.feature.coin.freelancer.dto.PurchaseCoinRequest;
import com.springboot.jobseeker.feature.coin.freelancer.service.FreelancerCoinService;
import com.springboot.jobseeker.shared.data.dto.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/v1/job-seeker/freelancer/coin")
@RestController
@AllArgsConstructor
@Tag(name="Freelancer Coin")
public class FreelancerCoinController {
    private final FreelancerCoinService freelancerCoinService;

    @PostMapping("/purchase")
    public ResponseEntity<?> purchaseCoin(
            @RequestBody PurchaseCoinRequest purchaseCoinRequest
    ){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        try{
            Long historyId = freelancerCoinService.purchaseCoin(purchaseCoinRequest, username);
            return ResponseEntity.ok(
                    ApiResponse.builder()
                            .status(HttpStatus.CREATED.value())
                            .data(historyId)
                            .message("Purchased Coin Successfully")
                            .build()
            );
        }catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
