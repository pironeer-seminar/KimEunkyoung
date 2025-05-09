package com.example.demo1.common.type;

public enum UserSuccessType implements SuccessType {
    CREATE("USER_1", "회원가입에 성공했습니다.");

    private final String code;
    private final String message;

    UserSuccessType(String code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public String getCode() { return code; }

    @Override
    public String getMessage() { return message; }
}