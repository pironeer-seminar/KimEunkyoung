package com.example.pironeer.controller;


import com.example.pironeer.dto.request.PostCreateReq;
import com.example.pironeer.dto.request.PostUpdateReq;
import com.example.pironeer.dto.response.PostSearchRes;
import com.example.pironeer.service.LikeService;
import com.example.pironeer.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;
    private final LikeService likeService;

    //생성
    @PostMapping("")
    public Long create(@RequestBody PostCreateReq req) {

        return postService.create(req);
    }

    @GetMapping("")
    //목록 조회
    public List<PostSearchRes> search() {

        return postService.search();
    }


    //단일조회
    @GetMapping("/{postId}")
    public PostSearchRes detail(
            @PathVariable("postId") Long postId
    ) {
        return postService.detail(postId);
    }


    //수정
//    @PutMapping("/{postId}")
//    public Long update(
//            @PathVariable("postId") Long postId,
//            @RequestBody PostCreateReq req) {
//        return postService.update(postId, req);
//    }

    @PutMapping("/{postId}")
    public Long update(
            @PathVariable("postId") Long postId,
            @RequestBody PostUpdateReq req) {
        return postService.update(postId, req);
    }


    //삭제
    @DeleteMapping("/{postId}")
    public Long delete(
            @PathVariable("postId") Long postId
    ) {
        return postService.delete(postId);
    }

    // 특정 유저가 작성한 게시글 전체 조회
    @GetMapping("/user/{userId}")
    public List<PostSearchRes> getPostsByUser(@PathVariable Long userId) {
        return postService.getPostsByUser(userId);
    }

    //좋아요 기능
    @PostMapping("/{postId}/like")
    public ResponseEntity<String> toggleLike(
            @PathVariable Long postId,
            @RequestParam Long userId
    ) {
        String result = likeService.toggleLike(userId, postId);
        return ResponseEntity.ok(result);
    }

}
