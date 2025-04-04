package com.example.pironeer.service;

import com.example.pironeer.entity.Like;
import com.example.pironeer.entity.Post;
import com.example.pironeer.entity.User;
import com.example.pironeer.repository.LikeRepository;
import com.example.pironeer.repository.PostRepository;
import com.example.pironeer.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LikeService {
    private final UserRepository userRepository;
    private final PostRepository postRepository;
    private final LikeRepository likeRepository;

    @Transactional
    public String toggleLike(Long userId, Long postId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("유저 없음"));
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("게시글 없음"));

        boolean alreadyLiked = likeRepository.existsByUserAndPost(user, post);

        if (alreadyLiked) {
            likeRepository.deleteByUserAndPost(user, post);
            return "좋아요 취소됨";
        } else {
            Like like = Like.create(user, post);
            likeRepository.save(like);
            return "좋아요 등록됨";
        }
    }

    public long countLikes(Long postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("게시글 없음"));
        return likeRepository.countByPost(post);
    }
}
