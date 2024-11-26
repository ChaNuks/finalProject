package com.UK.finalProject.exception;

import org.springframework.http.HttpStatus;

public enum ErrorCode {

    // 클라이언트 에러
    UNAUTHORIZED(HttpStatus.UNAUTHORIZED, "C001", "인증 정보가 올바르지 않습니다."),
    INVALID_EMAIL(HttpStatus.BAD_REQUEST, "C002", "이메일은 최대 50자까지 입력할 수 있습니다."),
    NOT_FOUND_MEMBER(HttpStatus.NOT_FOUND, "C003", "존재하지 않는 회원입니다."),
    WRONG_EMAIL(HttpStatus.UNAUTHORIZED, "C004", "이메일 정보가 올바르지 않습니다."),
    WRONG_PASSWORD(HttpStatus.UNAUTHORIZED, "C005", "비밀번호 정보가 올바르지 않습니다."),
    EMPTY_EMAIL(HttpStatus.BAD_REQUEST, "C006", "이메일을 입력해 주세요."),
    EMPTY_PASSWORD(HttpStatus.BAD_REQUEST, "C007", "비밀번호를 입력해 주세요."),
    EMPTY_NAME(HttpStatus.BAD_REQUEST, "C008", "이름을 입력해 주세요."),

    // 게시글 에러
    EMPTY_TITLE(HttpStatus.BAD_REQUEST, "B001", "제목을 입력해 주세요."),
    EMPTY_CONTENT(HttpStatus.BAD_REQUEST, "B002", "내용을 입력해 주세요."),
    EMPTY_CATEGORY(HttpStatus.BAD_REQUEST, "B003", "카테고리를 선택해 주세요."),
    IMAGE_UPLOAD_FAILED(HttpStatus.INTERNAL_SERVER_ERROR, "B004", "이미지 업로드에 실패했습니다."),
    POST_NOT_FOUND(HttpStatus.NOT_FOUND, "B005", "존재하지 않는 게시글입니다.");

    private final HttpStatus httpStatus;
    private final String code;
    private final String message;

    ErrorCode(HttpStatus httpStatus, String code, String message) {
        this.httpStatus = httpStatus;
        this.code = code;
        this.message = message;
    }

    public HttpStatus getHttpStatus() {
        return this.httpStatus;
    }

    public String getCode() {
        return this.code;
    }

    public String getMessage() {
        return this.message;
    }
}
