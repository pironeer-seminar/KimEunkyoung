package com.example.week6.security;

import com.example.week6.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import com.example.week6.entity.Member;


@Component("postSecurity")
@RequiredArgsConstructor
public class PostSecurity {
    private final PostRepository postRepository;

    public boolean isOwner(Long postId, Member member) {
        return postRepository.findById(postId)
                .map(post -> post.getAuthor().getId().equals(member.getId()))
                .orElse(false);
    }
}
