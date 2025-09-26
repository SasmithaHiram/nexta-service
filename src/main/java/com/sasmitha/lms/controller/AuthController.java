package com.sasmitha.lms.controller;

import com.sasmitha.lms.config.JWTUtil;
import com.sasmitha.lms.entity.UserEntity;
import com.sasmitha.lms.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@CrossOrigin
public class AuthController {
    private final AuthService userService;
    private final JWTUtil jwtUtil;

    @PostMapping("/register")
    public UserResponse userRegister(@RequestBody RegisterRequest registerRequest) {
        UserEntity register = userService.userRegister(registerRequest);

        return new UserResponse(
                register.getId(),
                register.getUsername(),
                register.getEmail(),
                register.getRole().name()
        );
    }

    @PostMapping("/login")
    public AuthResponse userLogin(@RequestBody UserLoginRequest userLoginRequest) {
        return userService.login(userLoginRequest);
    }

    public String resetPassword(@RequestBody String email) {
        return userService.resetPassword(email);
    }
}
