package com.example.springtest.user.exception;

import com.example.springtest.common.exception.BaseException;
import com.example.springtest.common.exception.ErrorType;

import org.springframework.http.HttpStatus;

public class DuplicatedEmailException extends BaseException {

    public DuplicatedEmailException() {
        super(ErrorType.DUPLICATED_EMAIL, HttpStatus.CONFLICT);
    }

}
