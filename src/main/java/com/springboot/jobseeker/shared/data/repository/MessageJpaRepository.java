package com.springboot.jobseeker.shared.data.repository;

import com.springboot.jobseeker.feature.message.dto.Message;
import com.springboot.jobseeker.shared.data.model.ChatMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageJpaRepository extends JpaRepository<ChatMessage, Long> {
}
