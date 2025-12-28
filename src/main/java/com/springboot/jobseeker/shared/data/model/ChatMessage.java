package com.springboot.jobseeker.shared.data.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name="chat_messages")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ChatMessage extends MasterEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private User fromUser;

    @ManyToOne(fetch = FetchType.LAZY)
    private User toUser;

    @ManyToOne(fetch = FetchType.LAZY)
    private JobPost jobPost;

    private String content;
}
