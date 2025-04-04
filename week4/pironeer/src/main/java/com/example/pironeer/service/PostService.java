package com.example.pironeer.service;

import com.example.pironeer.dto.request.PostCreateReq;
import com.example.pironeer.dto.request.PostUpdateReq;
import com.example.pironeer.dto.response.CommentRes;
import com.example.pironeer.dto.response.PostSearchRes;
import com.example.pironeer.entity.Comment;
import com.example.pironeer.entity.Post;
import com.example.pironeer.entity.PostStatus;
import com.example.pironeer.entity.User;
import com.example.pironeer.repository.CommentRepository;
import com.example.pironeer.repository.LikeRepository;
import com.example.pironeer.repository.PostRepository;
import com.example.pironeer.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final CommentRepository commentRepository;
    private final LikeRepository likeRepository;

    public Long create(PostCreateReq req) {
        User user = userRepository.findById(req.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("조회된 유저가 없습니다."));

        Post post = Post.create(user, req.getTitle(), req.getContent(), req.getStatus());
        post = postRepository.save(post);

        return post.getId();
    }

    public List<PostSearchRes> search() {
        // PostStatus가 public인 게시글만 조회할 수 있다.
        List<Post> posts = postRepository.findAllByStatus(PostStatus.PUBLIC);
        return posts.stream()
                .map(post -> new PostSearchRes(post.getUser().getId(), post.getId(), post.getTitle(), post.getContent(), post.getCreatedAt()))
                .toList();
    }

    public PostSearchRes detail(Long postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("조회된 게시글이 없습니다."));


//        return new PostSearchRes(post.getUserId().getId(), post.getId(), post.getTitle(),
//                post.getContent(), post.getCreatedAt());

//        return new PostSearchRes(
//                post.getUser().getId(),
//                post.getId(),
//                post.getTitle(),
//                post.getContent(),
//                post.getCreatedAt()
//        );


        List<Comment> comments = commentRepository.findAllByPost(post);
        long likeCount = likeRepository.countByPost(post);

        List<CommentRes> commentResList = comments.stream()
                .map(c -> new CommentRes(c.getUser().getName(), c.getContent(), c.getCreatedAt()))
                .toList();

        return new PostSearchRes(
                post.getUser().getId(),
                post.getId(),
                post.getTitle(),
                post.getContent(),
                post.getCreatedAt(),
                commentResList,
                likeCount

        );

    }

    public Long update(Long postId, PostUpdateReq req) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("조회된 게시글이 없습니다."));

        post.update(req.getTitle(), req.getContent(), req.getStatus());
        postRepository.save(post);

        return post.getId();
    }

    public Long delete(Long postId) {
        postRepository.deleteById(postId);
        return postId;
    }

    public List<PostSearchRes> getPostsByUser(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("조회된 유저가 없습니다."));

        List<Post> posts = postRepository.findAllByUser(user);

        return posts.stream()
                .map(post -> new PostSearchRes(
                        user.getId(),
                        post.getId(),
                        post.getTitle(),
                        post.getContent(),
                        post.getCreatedAt()
                ))
                .toList();
    }

}