package com.example.pironeer.service;

import com.example.pironeer.dto.request.UserCreateReq;
import com.example.pironeer.entity.User;
import com.example.pironeer.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@Transactional
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    //    public UserCreateRes create(UserCreateReq req) {
//        User user = User.create(req.getName());
//        user = userRepository.save(user);
//
//        return new UserCreateRes(user.getId());
//    }


    public Long create(UserCreateReq req) {
        User user = User.create(req.getName());
        user = userRepository.save(user);

        return user.getId();
    }
}