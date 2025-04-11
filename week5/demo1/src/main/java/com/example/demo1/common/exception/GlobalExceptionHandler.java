package com.example.demo1.common.exception;

import com.example.demo1.common.dto.ApiRes;
import com.example.demo1.common.type.CommonErrorType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ApiRes<?>> handlerIllegalArgumentException(
            final IllegalArgumentException ex) {
        log.error(ex.getMessage());
        return new ResponseEntity<>(
                ApiRes.fail(CommonErrorType.INTERNAL_SERVER, HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage()),
                HttpStatus.INTERNAL_SERVER_ERROR
        );
    }

    @ExceptionHandler(BaseException.class)
    public ResponseEntity<ApiRes<?>> handleCustomException(BaseException ex) {
        log.error(ex.getMessage());
        return new ResponseEntity<>(ApiRes.fail(ex), ex.getHttpStatus());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiRes<?>> handleValidationException(MethodArgumentNotValidException ex) {
        log.error("Validation error: {}", ex.getMessage());
        FieldError fieldError = ex.getBindingResult().getFieldError();
        String errorMessage = (fieldError != null) ? fieldError.getDefaultMessage() : "유효성 검사 실패";

        return new ResponseEntity<>(
                ApiRes.fail(CommonErrorType.INVALID_REQUEST, HttpStatus.BAD_REQUEST, errorMessage),
                HttpStatus.BAD_REQUEST
        );
    }
}
