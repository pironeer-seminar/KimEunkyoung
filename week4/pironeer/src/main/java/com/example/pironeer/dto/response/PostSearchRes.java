package com.example.pironeer.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@AllArgsConstructor
public class PostSearchRes {

    // 반환할 필드들 생성
    private Long userId;
    private Long postId;
    private String title;
    private String content;
    private LocalDateTime createdAt;
    private List<CommentRes> comments;
    private long likeCount;

    // 댓글 없이 사용할 때 (예: 게시글 목록 조회용)
    public PostSearchRes(Long userId, Long postId, String title, String content, LocalDateTime createdAt) {
        this.userId = userId;
        this.postId = postId;
        this.title = title;
        this.content = content;
        this.createdAt = createdAt;
        this.likeCount = 0;
        this.comments = List.of(); // 빈 리스트로 기본값 설정
    }

}