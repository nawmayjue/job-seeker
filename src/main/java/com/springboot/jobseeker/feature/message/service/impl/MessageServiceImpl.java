package com.springboot.jobseeker.feature.message.service.impl;

import com.springboot.jobseeker.feature.message.dto.Message;
import com.springboot.jobseeker.feature.message.dto.CreateMessageRequest;
import com.springboot.jobseeker.feature.message.dto.MessageResponse;
import com.springboot.jobseeker.feature.message.service.MessageService;
import com.springboot.jobseeker.feature.user.repository.jpa.UserJpaRepository;
import com.springboot.jobseeker.shared.data.model.ChatMessage;
import com.springboot.jobseeker.shared.data.model.User;
import com.springboot.jobseeker.shared.data.repository.MessageJpaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class MessageServiceImpl implements MessageService {

    private final MessageJpaRepository messageRepository;
    private final UserJpaRepository userJpaRepository;

    @Override
    public MessageResponse sendMessage(CreateMessageRequest createMessageRequest, String senderUsername) {
        User sender = userJpaRepository.findByLoginUsername(senderUsername)
                .orElseThrow(() -> new RuntimeException("User with username " + senderUsername + " doesn't exist"));
        User receiver = userJpaRepository.findById(createMessageRequest.getReceiverId())
                .orElseThrow(() -> new RuntimeException("User with id " + createMessageRequest.getReceiverId() + " doesn't exist"));

        ChatMessage message = ChatMessage.builder()
                .fromUser(sender)
                .toUser(receiver)
                .content(createMessageRequest.getMessageContent())
                .build();

        ChatMessage savedMessage = messageRepository.save(message);

        return MessageResponse.builder()
                .id(savedMessage.getId())
                .messageContent(savedMessage.getContent())
                .senderId(savedMessage.getFromUser().getId())
                .senderUsername(savedMessage.getFromUser().getLoginUsername())
                .receiverId(savedMessage.getToUser().getId())
                .receiverUsername(savedMessage.getToUser().getLoginUsername())
                .build();
    }
}
