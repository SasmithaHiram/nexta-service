package com.sasmitha.lms.controller;

import com.sasmitha.lms.config.JWTUtil;
import com.sasmitha.lms.dto.UserLoginRequest;
import com.sasmitha.lms.dto.UserRegisterRequest;
import com.sasmitha.lms.dto.UserResponse;
import com.sasmitha.lms.entity.UserEntity;
import com.sasmitha.lms.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@CrossOrigin
public class AuthController {
    private final UserService userService;
    private final JWTUtil jwtUtil;

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

    @PostMapping("/login")
    public Map<String, String> userLogin(@RequestBody UserLoginRequest userLoginRequest) {
        UserEntity login = userService.login(userLoginRequest);
        String token = jwtUtil.generateToken(login.getEmail());
        System.out.println(token);
        return Map.of("token", token);
    }
}
