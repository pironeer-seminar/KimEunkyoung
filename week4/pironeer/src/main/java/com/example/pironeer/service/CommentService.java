package com.example.pironeer.service;

import com.example.pironeer.dto.request.CommentCreateReq;
import com.example.pironeer.entity.Comment;
import com.example.pironeer.entity.Post;
import com.example.pironeer.entity.User;
import com.example.pironeer.repository.CommentRepository;
import com.example.pironeer.repository.PostRepository;
import com.example.pironeer.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final PostRepository postRepository;

    // 댓글 작성
    @Transactional
    public Long create(CommentCreateReq req) {
        User user = userRepository.findById(req.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("유저 없음"));
        Post post = postRepository.findById(req.getPostId())
                .orElseThrow(() -> new IllegalArgumentException("게시글 없음"));

        Comment comment = Comment.create(user, post, req.getContent());
        commentRepository.save(comment);
        return comment.getId();
    }

    // 댓글 삭제
    @Transactional
    public void delete(Long commentId) {
        commentRepository.deleteById(commentId);
    }
}
