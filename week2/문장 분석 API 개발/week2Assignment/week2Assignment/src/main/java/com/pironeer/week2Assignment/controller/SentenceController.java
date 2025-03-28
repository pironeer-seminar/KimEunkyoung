package com.pironeer.week2Assignment.controller;

import com.pironeer.week2Assignment.dto.SentenceRequestDto;
import com.pironeer.week2Assignment.dto.SentenceResponseDto;
import com.pironeer.week2Assignment.service.SentenceService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/analyze")
@RequiredArgsConstructor
public class SentenceController {

    private final SentenceService sentenceService;

    @PostMapping
    public ResponseEntity<SentenceResponseDto> analyzeSentence(@Valid @RequestBody SentenceRequestDto requestDto) {
        SentenceResponseDto responseDto = sentenceService.analyzeSentence(requestDto);
        return ResponseEntity.ok(responseDto);
    }
}
