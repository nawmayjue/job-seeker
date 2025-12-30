package com.springboot.jobseeker.shared.data.repository.jpa;

import com.springboot.jobseeker.shared.data.model.PurchaseHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PurchaseHistoryJpaRepository extends JpaRepository<PurchaseHistory, Long> {
}
