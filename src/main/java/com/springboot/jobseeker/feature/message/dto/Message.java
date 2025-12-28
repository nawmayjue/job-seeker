package com.springboot.jobseeker.feature.message.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Message {
    private String type; // Message, Typing
    private String content;
    private String sender;
    private String receiver;
    private Long senderId;
    private Long receiverId;
    private String timestamp;
}
