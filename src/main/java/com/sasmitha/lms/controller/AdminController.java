package com.sasmitha.lms.controller;

import com.sasmitha.lms.dto.UserRegisterRequest;
import com.sasmitha.lms.dto.UserResponse;
import com.sasmitha.lms.entity.UserEntity;
import com.sasmitha.lms.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
@CrossOrigin
public class AdminController {
    private final UserService userService;

    @PostMapping("/create-instructor")
    @PreAuthorize("hasRole('ADMIN')")
    public UserResponse createInstructor(@RequestBody UserRegisterRequest userRegisterRequest) {
        UserEntity register = userService.instructorRegister(userRegisterRequest);

        return new UserResponse(
                register.getId(),
                register.getName(),
                register.getEmail(),
                register.getRole().name()
        );
    }
}
