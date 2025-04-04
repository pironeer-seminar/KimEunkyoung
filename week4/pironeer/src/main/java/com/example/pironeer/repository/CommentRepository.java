package com.example.pironeer.repository;


import com.example.pironeer.entity.Comment;
import com.example.pironeer.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findAllByPost(Post post);
}

