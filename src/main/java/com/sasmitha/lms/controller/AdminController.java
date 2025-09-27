package com.sasmitha.lms.controller;

import com.sasmitha.lms.dto.RegisterRequest;
import com.sasmitha.lms.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
@CrossOrigin
public class AdminController {
    private final UserService userService;

    public void createUser(@RequestBody RegisterRequest registerRequest) {
        userService.createUser(registerRequest);
    }
}
