package com.UK.finalProject.common.auth.service.response;

import lombok.Getter;

@Getter
public class BaseResponse<T> {

    private final String code;
    private final String message;
    private T result;

    // 성공 응답
    public BaseResponse(String code, String message, T result) {
        this.code = code;
        this.message = message;
        this.result = result;
    }

    // 실패 응답
    public BaseResponse(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
