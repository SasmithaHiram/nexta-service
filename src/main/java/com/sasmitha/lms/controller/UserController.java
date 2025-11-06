package com.sasmitha.lms.controller;

import com.sasmitha.lms.service.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@CrossOrigin
public class UserController {
    private final UserServiceImpl userServiceImpl;

    @PostMapping("/{moduleId}/assign-users")
    @PreAuthorize("hasAuthority('SYSTEM_ADMIN')")
    public ResponseEntity<String> assignUsersToModule(@PathVariable Long moduleId, @RequestBody List<Long> userIds) {
        userServiceImpl.assignUsersToModule(moduleId, userIds);
        return ResponseEntity.ok("Users assigned to module successfully");
    }

}
