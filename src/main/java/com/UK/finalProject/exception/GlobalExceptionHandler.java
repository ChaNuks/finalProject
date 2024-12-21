package com.UK.finalProject.exception;

import com.UK.finalProject.common.auth.service.response.BaseResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice   // 전역 예외 처리 정의
public class GlobalExceptionHandler {

    // 특정 예외 처리 (사용자 젇의)
    @ExceptionHandler(CustomException.class)
    public ResponseEntity<BaseResponse<String>> HandleCustomException(CustomException e) {

        ErrorCode errorCode = e.getErrorCode();

        BaseResponse<String> failResponse = new BaseResponse<>(errorCode.getCode(), errorCode.getMessage());

        return ResponseEntity
                .status(errorCode.getHttpStatus())
                .body(failResponse);
    }
}
