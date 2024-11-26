package com.UK.finalProject.exception;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
//@Builder
public class ErrorResponse {

    private final String code;
    private final String message;
    private final String timeStamp;

    public ErrorResponse(String code, String message) {
        this.code = code;
        this.message = message;
        this.timeStamp = LocalDateTime.now().toString();
    }
}
