package com.example.demo1.common.type;

public enum CommonErrorType implements ErrorType {

    INTERNAL_SERVER("COMMON_1", "알 수 없는 서버 에러가 발생했습니다");

    private final String code;
    private final String message;

    CommonErrorType(String code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public String getCode() {
        return this.code;
    }

    @Override
    public String getMessage() {
        return this.message;
    }
}