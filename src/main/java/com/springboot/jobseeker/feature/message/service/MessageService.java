package com.springboot.jobseeker.feature.message.service;

import com.springboot.jobseeker.feature.message.dto.CreateMessageRequest;
import com.springboot.jobseeker.feature.message.dto.MessageResponse;

public interface MessageService {
    MessageResponse sendMessage(CreateMessageRequest createMessageRequest, String senderUsername);
}
