package com.springboot.jobseeker.shared.data.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ApiResponse {
    private int status;
    private Object data;
    private String message;
}
