package com.example.week6.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter
@NoArgsConstructor
public class RefreshToken {

    @Id
    private String username;  // 회원 아이디 (username)

    private String token;     // 실제 refresh token 문자열

    public RefreshToken(String username, String token) {
        this.username = username;
        this.token = token;
    }
}
