package com.springboot.jobseeker.shared.data.dto;

import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@SuperBuilder
public abstract class MasterDto{
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
