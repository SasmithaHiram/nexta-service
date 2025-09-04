package com.sasmitha.lms.controller;

import com.sasmitha.lms.dto.UserRegisterRequest;
import com.sasmitha.lms.dto.UserResponse;
import com.sasmitha.lms.entity.UserEntity;
import com.sasmitha.lms.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@CrossOrigin
public class AuthController {
    private final UserService userService;

    @PostMapping("/register")
    public UserResponse userRegister(@RequestBody UserRegisterRequest userRegisterRequest) {
        UserEntity register = userService.register(userRegisterRequest);

        return new UserResponse(
                register.getId(),
                register.getName(),
                register.getEmail(),
                register.getRole().name()
        );

    }


}
