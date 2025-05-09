package com.example.demo1.post.entity;

import com.example.demo1.common.entity.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Getter
@Builder(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@EntityListeners(AuditingEntityListener.class)
public class Post extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String title;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String content;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 100)
    private PostStatus status;

    @ManyToOne
    @JoinColumn(nullable = false, name = "user_id")
    private com.example.demo1.user.entity.User user;


    @CreatedDate
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;
    public static Post create(com.example.demo1.user.entity.User author, String title, String content, PostStatus status) {
        return Post.builder()
                .user(author)
                .title(title)
                .content(content)
                .status(status)
                .build();
    }

    public void update(String title, String content, PostStatus status) {
        this.title = title;
        this.content = content;
        this.status = status;
    }
}
