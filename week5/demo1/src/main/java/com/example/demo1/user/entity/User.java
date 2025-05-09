package com.example.demo1.user.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Table(name = "users")
@Builder(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50)
    private String name;


    public static User create(String name) {
        return User.builder()
                .name(name)
                .build();
    }
}

