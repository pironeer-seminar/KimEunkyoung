//package com.example.week6.jwt;
//
//import io.jsonwebtoken.*;
//import jakarta.annotation.PostConstruct;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Component;
//
//import java.security.Key;
//import java.util.Date;
//
//@Component
//public class JwtTokenProvider {
//
//    @Value("${jwt.secret}")
//    private String secretKey;
//
//    @Value("${jwt.access-token-expiration}")
//    private long accessTokenExpiration;
//
//    @Value("${jwt.refresh-token-expiration}")
//    private long refreshTokenExpiration;
//
//    private Key key;
//
//    @PostConstruct
//    protected void init() {
//        // secretKey는 최소 32자 이상 권장
//        this.secretKey = secretKey.trim();
//        this.key = Key.hmacShaKeyFor(secretKey.getBytes());
//    }
//
////    public String getUsername(String token) {
////        return Jwts.parser()
////                .setSigningKey(secretKey.getBytes())
////                .parseClaimsJws(token)
////                .getBody()
////                .getSubject();
////    }
//
//    public String getUsername(String token) {
//        return Jwts.parserBuilder()
//                .setSigningKey(key) // 변경됨
//                .build()
//                .parseClaimsJws(token)
//                .getBody()
//                .getSubject();
//    }
//
//    public boolean validateToken(String token) {
//        try {
//            Jwts.parser().setSigningKey(secretKey.getBytes()).parseClaimsJws(token);
//            return true;
//        } catch (JwtException | IllegalArgumentException e) {
//            return false;
//        }
//    }
//
//    public String createAccessToken(String username) {
//        return createToken(username, accessTokenExpiration);
//    }
//
//    public String createRefreshToken(String username) {
//        return createToken(username, refreshTokenExpiration);
//    }
//
//    private String createToken(String username, long expirationTime) {
//        Date now = new Date();
//        Date expiry = new Date(now.getTime() + expirationTime);
//
//        return Jwts.builder()
//                .setSubject(username)
//                .setIssuedAt(now)
//                .setExpiration(expiry)
//                .signWith(SignatureAlgorithm.HS256, secretKey.getBytes())
//                .compact();
//    }
//
//}


package com.example.week6.jwt;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtTokenProvider {

    @Value("${jwt.secret}")
    private String secretKey;

    @Value("${jwt.access-token-expiration}")
    private long accessTokenExpiration;

    @Value("${jwt.refresh-token-expiration}")
    private long refreshTokenExpiration;

    private Key key;

    @PostConstruct
    protected void init() {
        this.secretKey = secretKey.trim();
        this.key = Keys.hmacShaKeyFor(secretKey.getBytes()); // Key 객체로 변환
    }

    public String getUsername(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key) // 변경됨
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }

    public String createAccessToken(String username) {
        return createToken(username, accessTokenExpiration);
    }

    public String createRefreshToken(String username) {
        return createToken(username, refreshTokenExpiration);
    }

    private String createToken(String username, long expirationTime) {
        Date now = new Date();
        Date expiry = new Date(now.getTime() + expirationTime);

        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(now)
                .setExpiration(expiry)
                .signWith(key, SignatureAlgorithm.HS256) // 변경됨
                .compact();
    }
}
