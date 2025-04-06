package com.example.pironeer.repository;

import com.example.pironeer.entity.Post;
import com.example.pironeer.entity.PostStatus;
import com.example.pironeer.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
    //public인지 private인지 게시글 조회
    List<Post> findAllByStatus(PostStatus status);

    //특정 유저가 사용한 게시글 조회
    List<Post> findAllByUser(User user);
}
