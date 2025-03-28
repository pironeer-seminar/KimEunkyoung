package com.pironeer.week2Assignment.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SentenceRequestDto {
    @NotBlank(message = "문장은 필수 입력 항목입니다.")
    private String sentence;
}
