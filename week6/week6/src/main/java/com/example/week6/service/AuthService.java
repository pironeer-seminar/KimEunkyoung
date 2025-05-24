package com.example.week6.service;

import com.example.week6.dto.LoginRequest;
import com.example.week6.dto.SignupRequest;
import com.example.week6.dto.TokenRequest;
import com.example.week6.entity.Member;
import com.example.week6.entity.RefreshToken;
import com.example.week6.entity.Role;
import com.example.week6.jwt.JwtTokenProvider;
import com.example.week6.repository.MemberRepository;
import com.example.week6.repository.RefreshTokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;
    private final RefreshTokenRepository refreshTokenRepository;

    public void signup(SignupRequest request) {
        if (memberRepository.findByUsername(request.getUsername()).isPresent()) {
            throw new IllegalArgumentException("이미 존재하는 사용자명입니다.");
        }

        Member member = new Member();
        member.setUsername(request.getUsername());
        member.setPassword(passwordEncoder.encode(request.getPassword()));
        memberRepository.save(member);

        // 권한 부여
        member.setRole(Role.USER);

        memberRepository.save(member);
    }

//    public String login(LoginRequest request) {
//        Member member = memberRepository.findByUsername(request.getUsername())
//                .orElseThrow(() -> new IllegalArgumentException("가입되지 않은 사용자입니다."));
//
//        if (!passwordEncoder.matches(request.getPassword(), member.getPassword())) {
//            throw new IllegalArgumentException("비밀번호가 틀렸습니다.");
//        }
//
//        return jwtTokenProvider.createToken(member.getUsername());
//    }

    public Map<String, String> login(LoginRequest request) {
        Member member = memberRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new IllegalArgumentException("가입되지 않은 사용자입니다."));

        if (!passwordEncoder.matches(request.getPassword(), member.getPassword())) {
            throw new IllegalArgumentException("비밀번호가 틀렸습니다.");
        }

        String accessToken = jwtTokenProvider.createAccessToken(member.getUsername());
        String refreshToken = jwtTokenProvider.createRefreshToken(member.getUsername());

        // DB에 저장
        refreshTokenRepository.save(new RefreshToken(member.getUsername(), refreshToken));

        Map<String, String> tokens = new HashMap<>();
        tokens.put("accessToken", accessToken);
        tokens.put("refreshToken", refreshToken);
        return tokens;
    }

    public String reissueAccessToken(TokenRequest request) {
        String refreshToken = request.getRefreshToken();

        // 1. 토큰 유효성 검사
        if (!jwtTokenProvider.validateToken(refreshToken)) {
            throw new IllegalArgumentException("Refresh Token 유효하지 않음");
        }

        // 2. DB에 저장된 리프레시 토큰인지 확인
        RefreshToken token = refreshTokenRepository.findByToken(refreshToken)
                .orElseThrow(() -> new IllegalArgumentException("DB에 없는 Refresh Token"));

        // 3. 새 AccessToken 생성
        return jwtTokenProvider.createAccessToken(token.getUsername());
    }


}
