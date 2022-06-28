package com.example.springtest.common.exception;

import lombok.Getter;

@Getter
public enum ErrorType {

    NOT_FOUND_USER("MEMBER-001", "사용자를 찾을 수 없습니다."),
    DUPLICATED_EMAIL("MEMBER-002", "사용 중인 이메일입니다.");

    private final String code;
    private final String message;

    ErrorType(String code, String message) {
        this.code = code;
        this.message = message;
    }

}
