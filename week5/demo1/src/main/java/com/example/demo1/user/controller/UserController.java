package com.example.demo1.user.controller;

import com.example.demo1.common.dto.ApiRes;
import com.example.demo1.common.type.UserSuccessType;
import com.example.demo1.user.dto.request.UserCreateReq;
import com.example.demo1.user.dto.response.UserCreateRes;
import com.example.demo1.user.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
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

//    @PostMapping("")
//    public Long create(@RequestBody UserCreateReq req) {
//        return userService.create(req);
//    }


//    @PostMapping("")
//    public ApiRes<UserCreateRes> create(@RequestBody @Valid UserCreateReq req) {
//        Long userId = userService.create(req);
//        UserCreateRes res = new UserCreateRes(userId, "회원가입이 완료되었습니다.");
//        return ApiRes.success(res);
//    }

    @PostMapping("")
    @Operation(summary = "회원가입 API", description = "사용자 정보를 받아 회원가입을 수행합니다.")
    public ApiRes<UserCreateRes> create(@RequestBody @Valid UserCreateReq req) {
        Long userId = userService.create(req);
        UserCreateRes res = new UserCreateRes(userId, UserSuccessType.CREATE.getMessage());
        return ApiRes.success(UserSuccessType.CREATE, res);
    }
}
