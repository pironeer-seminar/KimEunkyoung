package com.example.demo1.post.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class PostSearchRes {

    @Schema(description = "작성자 ID", example = "1")
    private Long userId;

    @Schema(description = "게시글 ID", example = "10")
    private Long postId;

    @Schema(description = "제목", example = "pironeer")
    private String title;

    @Schema(description = "내용", example = "content")
    private String content;

    @Schema(description = "작성 시간", example = "2024-04-11T15:00:00")
    private LocalDateTime createdAt;
}
