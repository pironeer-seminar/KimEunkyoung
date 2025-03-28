package com.pironeer.week2Assignment.dto;

import lombok.Getter;

@Getter
public class SentenceResponseDto {
    private int length;  // 문장 길이
    private int wordCount;  // 단어 개수
    private boolean containsSpring;  // 특정 단어 포함 여부

    public SentenceResponseDto(int length, int wordCount, boolean containsSpring) {
        this.length = length;
        this.wordCount = wordCount;
        this.containsSpring = containsSpring;
    }
}
