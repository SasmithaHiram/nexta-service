package com.sasmitha.lms.controller;

import com.sasmitha.lms.dto.RegisterResponse;
import com.sasmitha.lms.service.AuthServiceImpl;
import com.sasmitha.lms.dto.LoginRequest;
import com.sasmitha.lms.dto.LoginResponse;
import com.sasmitha.lms.dto.UserRegisterRequest;
import lombok.RequiredArgsConstructor;
//import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@CrossOrigin
public class AuthController {
    private final AuthServiceImpl authServiceImpl;

    @PostMapping("/register")
    @PreAuthorize("hasAuthority('SYSTEM_ADMIN')")
    public ResponseEntity<RegisterResponse> create(@RequestBody UserRegisterRequest userRegisterRequest) {
        RegisterResponse user = authServiceImpl.createUser(userRegisterRequest);

        if (user == null) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        } else {
            return new ResponseEntity<>(user, HttpStatus.OK);
        }
    }

    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest loginRequest) {
        return authServiceImpl.loginUser(loginRequest);
    }
}
