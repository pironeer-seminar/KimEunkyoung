package com.pironeer.week2Assignment.service;

import com.pironeer.week2Assignment.dto.SentenceRequestDto;
import com.pironeer.week2Assignment.dto.SentenceResponseDto;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class SentenceService {

    public SentenceResponseDto analyzeSentence(SentenceRequestDto requestDto) {
        String sentence = requestDto.getSentence();
        int length = sentence.length();
        int wordCount = sentence.split("\\s+").length;
        boolean containsSpring = sentence.toLowerCase().contains("spring");

        return new SentenceResponseDto(length, wordCount, containsSpring);
    }
}
