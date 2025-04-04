package com.example.pironeer.repository;

import com.example.pironeer.entity.Like;
import com.example.pironeer.entity.Post;
import com.example.pironeer.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LikeRepository extends JpaRepository<Like, Long> {
    boolean existsByUserAndPost(User user, Post post);

    void deleteByUserAndPost(User user, Post post);

    long countByPost(Post post);
}