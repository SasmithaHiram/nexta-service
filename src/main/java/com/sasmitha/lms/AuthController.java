package com.sasmitha.lms;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@CrossOrigin
public class AuthController {
    private final AuthServiceImpl authServiceImpl;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/register")
    public void create(@RequestBody UserRegisterRequest userRegisterRequest) {
        authServiceImpl.createUser(userRegisterRequest);
    }
}
