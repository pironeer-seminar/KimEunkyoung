package com.example.week6.controller;

import com.example.week6.dto.LoginRequest;
import com.example.week6.dto.SignupRequest;
import com.example.week6.dto.TokenRequest;
import com.example.week6.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class AuthController {
    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@RequestBody LoginRequest request) {
        return ResponseEntity.ok(authService.login(request));
    }

    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody @Valid SignupRequest request) {
        authService.signup(request);
        return ResponseEntity.ok("회원가입 성공!");
    }

//    @PostMapping("/login")
//    public ResponseEntity<String> login(@RequestBody LoginRequest request) {
//        String token = authService.login(request);
//        return ResponseEntity.ok(token);
//    }

    @PostMapping("/reissue")
    public ResponseEntity<Map<String, String>> reissue(@RequestBody TokenRequest request) {
        String newAccessToken = authService.reissueAccessToken(request);
        return ResponseEntity.ok(Map.of("accessToken", newAccessToken));
    }

}
