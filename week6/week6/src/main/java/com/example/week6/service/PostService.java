package com.example.week6.service;

import com.example.week6.dto.PostRequest;
import com.example.week6.entity.Member;
import com.example.week6.entity.Post;
import com.example.week6.repository.PostRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;

    public void create(PostRequest request, Member member) {
        Post post = Post.builder()
                .title(request.getTitle())
                .content(request.getContent())
                .author(member)
                .build();
        postRepository.save(post);
    }

    @Transactional
    public void updatePost(Long postId, PostRequest request, Member member) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("게시글이 존재하지 않습니다."));

        post.setTitle(request.getTitle());
        post.setContent(request.getContent());
    }

    public void deleteAllPosts() {
        postRepository.deleteAll();
    }

}
