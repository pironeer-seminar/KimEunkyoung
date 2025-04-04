package com.example.pironeer.controller;

import com.example.pironeer.dto.request.CommentCreateReq;
import com.example.pironeer.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/comments")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    // 댓글 작성
    @PostMapping("")
    public ResponseEntity<Long> create(@RequestBody CommentCreateReq req) {
        Long id = commentService.create(req);
        return ResponseEntity.ok(id);
    }

    // 댓글 삭제
    @DeleteMapping("/{commentId}")
    public ResponseEntity<Void> delete(@PathVariable Long commentId) {
        commentService.delete(commentId);
        return ResponseEntity.noContent().build();
    }
}
