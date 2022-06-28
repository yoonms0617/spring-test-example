package com.example.springtest.common.exception;

import lombok.Getter;

import org.springframework.http.HttpStatus;

@Getter
public class BaseException extends RuntimeException {

    private final ErrorType errorType;

    private final HttpStatus httpStatus;

    public BaseException(ErrorType errorType, HttpStatus httpStatus) {
        super(errorType.getMessage());
        this.errorType = errorType;
        this.httpStatus = httpStatus;
    }

}
