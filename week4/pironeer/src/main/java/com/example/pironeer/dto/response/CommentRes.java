package com.example.pironeer.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class CommentRes {
    private String username;
    private String content;
    private LocalDateTime createdAt;
}
