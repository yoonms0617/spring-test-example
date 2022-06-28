package com.example.springtest.user.exception;

import com.example.springtest.common.exception.BaseException;
import com.example.springtest.common.exception.ErrorType;

import org.springframework.http.HttpStatus;

public class NotFoundUserException extends BaseException {

    public NotFoundUserException() {
        super(ErrorType.NOT_FOUND_USER, HttpStatus.NOT_FOUND);
    }

}
