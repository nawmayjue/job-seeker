package com.springboot.jobseeker.shared.data.dto;

public record ErrorResponse(
        int code,
        String message
) {}
