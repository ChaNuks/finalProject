package com.UK.finalProject;

import org.apache.logging.log4j.message.Message;
import org.springframework.http.HttpStatus;

public enum ErrorMessage {

    SUCCESS(HttpStatus.OK, "요청 성공"),
    CREATED(HttpStatus.CREATED, "생성 완료"),
    BAD_REQUEST(HttpStatus.BAD_REQUEST, "잘못된 요청"),
    NOT_FOUND(HttpStatus.NOT_FOUND, "찾을 수 없음"),
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "서버 장애");

    private final HttpStatus httpStatus;
    private final String message;

    ErrorMessage(HttpStatus httpStatus, String message) {
        this.httpStatus = httpStatus;
        this.message = message;
    }

    public HttpStatus getHttpStatus() {
        return this.httpStatus;
    }

    public String getMessage() {
        return this.message;
    }
}
