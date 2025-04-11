package com.example.demo1.common.exception;

import com.example.demo1.common.type.ErrorType;
import org.springframework.http.HttpStatus;

public class NotFoundException extends BaseException{
    public NotFoundException(ErrorType errorType) {
        super(errorType, HttpStatus.NOT_FOUND);
    }
}
