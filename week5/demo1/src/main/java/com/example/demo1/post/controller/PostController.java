package com.example.demo1.post.controller;

import com.example.demo1.common.dto.ApiRes;
import com.example.demo1.common.type.PostSuccessType;
import com.example.demo1.post.dto.request.PostCreateReq;
import com.example.demo1.post.dto.request.PostUpdateReq;
import com.example.demo1.post.dto.response.PostSearchRes;
import com.example.demo1.post.service.PostService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    // 생성
    @PostMapping("")
    public ApiRes<?> create(@RequestBody @Valid PostCreateReq req) {
        postService.create(req);
        return ApiRes.success(PostSuccessType.CREATE);
    }

    // 목록조회
    @GetMapping("")
    public ApiRes<List<PostSearchRes>> search() {
        return ApiRes.success(PostSuccessType.GET_ALL, postService.search());
    }


    // 단일조회
//    @GetMapping("/{postId}")
//    public PostSearchRes detail(
//            @PathVariable("postId") Long postId
//    ) {
//        return postService.detail(postId);
//    }
    @GetMapping("/{postId}")
    public ApiRes<PostSearchRes> detail(@PathVariable("postId") Long postId) {
        return ApiRes.success(PostSuccessType.GET_ONE, postService.detail(postId));
    }



    // 수정
//    @PutMapping("/{postId}")
//    public Long update(
//            @PathVariable("postId") Long postId,
//            @RequestBody PostUpdateReq req) {
//        return postService.update(postId, req);
//    }

    @PutMapping("/{postId}")
    public ApiRes<?> update(@PathVariable("postId") Long postId,
                            @RequestBody @Valid PostUpdateReq req) {
        postService.update(postId, req);
        return ApiRes.success(PostSuccessType.UPDATE);
    }


    // 삭제
//    @DeleteMapping("/{postId}")
//    public Long delete(
//            @PathVariable("postId") Long postId
//    ) {
//        return postService.delete(postId);
//    }

    @DeleteMapping("/{postId}")
    public ApiRes<?> delete(@PathVariable("postId") Long postId) {
        postService.delete(postId);
        return ApiRes.success(PostSuccessType.DELETE);
    }
}
