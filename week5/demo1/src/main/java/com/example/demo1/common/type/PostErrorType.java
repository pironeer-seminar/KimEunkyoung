package com.example.demo1.common.type;

public enum PostErrorType implements ErrorType {
    NOT_FOUND("POST_404", "해당 게시글을 찾을 수 없습니다."),
    NO_PERMISSION("POST_403", "게시글에 대한 권한이 없습니다.");

    private final String code;
    private final String message;

    PostErrorType(String code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public String getCode() { return code; }

    @Override
    public String getMessage() { return message; }
}
