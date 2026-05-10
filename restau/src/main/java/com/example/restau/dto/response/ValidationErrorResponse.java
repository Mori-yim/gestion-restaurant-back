package com.example.restau.dto.response;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Map;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ValidationErrorResponse {

    private String message;
    private int statusCode;
    private LocalDateTime timestamp;
    private Map<String, String> errors;

    public ValidationErrorResponse(int statusCode, String message, Map<String, String> errors) {
        this.timestamp = LocalDateTime.now();
        this.statusCode = statusCode;
        this.message = message;
        this.errors = errors;
    }
}