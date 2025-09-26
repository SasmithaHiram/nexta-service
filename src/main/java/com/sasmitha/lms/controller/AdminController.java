package com.sasmitha.lms.controller;

import com.sasmitha.lms.dto.User;
import com.sasmitha.lms.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
@CrossOrigin
public class AdminController {
    private final AuthService authService;

    @GetMapping("/users")
    @PreAuthorize("hasAuthority('ADMIN')")
    public List<User> getUsers() {
        return authService.users();
    }
}
