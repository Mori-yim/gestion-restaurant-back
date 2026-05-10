package com.example.restau.dto.response;



import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
//@AllArgsConstructor
public class MessageResponse {

    private String message;
    private boolean success;
    private Integer statusCode;

    public MessageResponse(String message) {
        this.message = message;
        this.success = true;
        this.statusCode = 200;
    }

    public MessageResponse(String message, boolean success) {
        this.message = message;
        this.success = success;
        this.statusCode = success ? 200 : 400;
    }

    public MessageResponse(String message, boolean success, Integer statusCode) {
        this.message = message;
        this.success = success;
        this.statusCode = statusCode;
    }
}
