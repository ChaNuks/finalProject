package com.UK.finalProject.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice   // 전역 예외 처리 정의
public class GlobalExceptionHandler {

    // 특정 예외 처리 (사용자 젇의)
    @ExceptionHandler(CustomException.class)
    public ResponseEntity<ErrorResponse> HandleCustomException(CustomException e) {

        ErrorCode errorCode = e.getErrorCode();

        return ResponseEntity
                .status(errorCode.getHttpStatus())
                .body(new ErrorResponse(errorCode.getCode(), errorCode.getMessage()));
    }
}
