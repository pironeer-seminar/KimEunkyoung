package com.example.week6.controller;


import com.example.week6.dto.PostRequest;
import com.example.week6.entity.Member;
import com.example.week6.security.MemberDetails;
import com.example.week6.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.access.prepost.PreAuthorize;


import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/posts")
public class PostController {
    private final PostService postService;

    @PostMapping
    public ResponseEntity<Map<String, String>> createPost(@RequestBody PostRequest request,
                                                          @AuthenticationPrincipal MemberDetails memberDetails) {
        Member member = memberDetails.getMember();
        postService.create(request, member);
        return ResponseEntity.ok(Map.of("message", "게시글 생성 성공"));
    }

    @PutMapping("/{id}")
    @PreAuthorize("@postSecurity.isOwner(#id, #memberDetails.member)")
    public ResponseEntity<Map<String, String>> updatePost(@PathVariable Long id,
                                                          @RequestBody PostRequest request,
                                                          @AuthenticationPrincipal MemberDetails memberDetails) {
        postService.updatePost(id, request, memberDetails.getMember());
        return ResponseEntity.ok(Map.of("message", "게시글 수정 성공"));
    }

    // PostController.java
    @DeleteMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Map<String, String>> deleteAllPosts() {
        postService.deleteAllPosts();
        return ResponseEntity.ok(Map.of("message", "전체 게시글 삭제 완료"));
    }


}
