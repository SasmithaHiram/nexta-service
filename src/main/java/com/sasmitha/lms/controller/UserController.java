package com.sasmitha.lms.controller;

import com.sasmitha.lms.dto.ModuleDTO;
import com.sasmitha.lms.service.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
@CrossOrigin
public class UserController {
    private final UserServiceImpl userServiceImpl;

    @PostMapping("/{moduleId}")
    @PreAuthorize("hasAuthority('SYSTEM_ADMIN')")
    public ResponseEntity<String> assignUsersToModule(@PathVariable Long moduleId, @RequestBody List<Long> userIds) {
        userServiceImpl.assignUsersToModule(moduleId, userIds);
        return ResponseEntity.ok("Users assigned to module successfully");
    }

    @GetMapping("/{studentId}/modules")
    @PreAuthorize("hasAuthority('SYSTEM_ADMIN')")
    public ResponseEntity<List<ModuleDTO>> modulesByUserId(@PathVariable Long studentId) {
        List<ModuleDTO> moduleByStudentId = userServiceImpl.modulesByStudentId(studentId);

        if (moduleByStudentId.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(moduleByStudentId);
        }
    }
}
