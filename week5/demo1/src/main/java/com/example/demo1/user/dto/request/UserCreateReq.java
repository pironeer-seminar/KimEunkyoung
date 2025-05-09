package com.example.demo1.user.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class UserCreateReq {

    @Schema(description = "사용자 이름", example = "user")
    @NotNull(message = "사용자 이름은 필수입니다.")
    private String name;
}
