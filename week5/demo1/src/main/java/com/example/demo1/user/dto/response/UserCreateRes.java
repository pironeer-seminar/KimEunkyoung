package com.example.demo1.user.dto.response;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserCreateRes {
    @Schema(description = "생성된 사용자 ID", example = "1")
    private Long userId;

    @Schema(description = "메시지", example = "완료되었습니다.")
    private String message;
}
