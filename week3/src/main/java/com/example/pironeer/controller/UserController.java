package com.example.pironeer.controller;

import com.example.pironeer.domain.User;
import com.example.pironeer.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    // 회원 등록
    @PostMapping
    public User registerUser(
            @RequestParam String username,
            @RequestParam String email
    ) {
        return userService.register(username, email);
    }

    // 전체 회원 조회
    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    // 단일 회원 조회
    @GetMapping("/{userId}")
    public User getUser(@PathVariable Long userId) {
        return userService.getUser(userId);
    }
}
