package com.example.restau.dto.response;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ErrorResponse {

    private String message;
    private String error;
    private int statusCode;
    private LocalDateTime timestamp;
    private String path;

    public ErrorResponse(int statusCode, String error, String message, String path) {
        this.timestamp = LocalDateTime.now();
        this.statusCode = statusCode;
        this.error = error;
        this.message = message;
        this.path = path;
    }
}
