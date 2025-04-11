package com.example.demo1.user.controller;

import com.example.demo1.user.dto.request.UserCreateReq;
import com.example.demo1.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("")
    public Long create(@RequestBody UserCreateReq req) {
        return userService.create(req);
    }

}
