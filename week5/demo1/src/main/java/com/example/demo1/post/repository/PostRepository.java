package com.example.demo1.post.repository;

import com.example.demo1.post.entity.Post;
import com.example.demo1.post.entity.PostStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {

    List<Post> findAllByStatus(PostStatus status);
}
