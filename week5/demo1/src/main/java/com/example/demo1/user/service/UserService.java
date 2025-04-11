package com.example.demo1.user.service;

import com.example.demo1.user.dto.request.UserCreateReq;
import com.example.demo1.user.entity.User;
import com.example.demo1.user.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public Long create(UserCreateReq req) {
        User user = User.create(req.getName());
        user = userRepository.save(user);

        return user.getId();
    }
}
