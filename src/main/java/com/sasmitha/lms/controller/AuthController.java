package com.sasmitha.lms.controller;

import com.sasmitha.lms.dto.UserRegisterRequest;
import com.sasmitha.lms.dto.UserResponse;
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
    public void userRegister(@RequestBody UserRegisterRequest userRegisterRequest) {
        userService.register(userRegisterRequest);
    }


}
