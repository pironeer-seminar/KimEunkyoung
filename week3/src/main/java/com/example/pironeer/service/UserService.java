package com.example.pironeer.service;

import com.example.pironeer.domain.User;
import com.example.pironeer.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    // 회원 등록
    public User register(String username, String email) {
        User user = new User(username, email);
        return userRepository.save(user);
    }

    // 단일 회원 조회
    public User getUser(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다."));
    }

    // 전체 회원 조회
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public Long createUser(User user) {
        return userRepository.save(user).getId();
    }

}