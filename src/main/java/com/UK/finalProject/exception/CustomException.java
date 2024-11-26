package com.UK.finalProject.exception;

import lombok.Getter;

// 커스트마이징한 예외
@Getter
public class CustomException extends RuntimeException {

    private final ErrorCode errorCode;

    public CustomException(final ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }
}
