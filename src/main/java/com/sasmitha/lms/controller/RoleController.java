package com.sasmitha.lms.controller;

import com.sasmitha.lms.dto.RoleDTO;
import com.sasmitha.lms.model.Role;
import com.sasmitha.lms.service.RoleServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@RequestMapping("roles")
@RequiredArgsConstructor
@CrossOrigin
public class RoleController {
    private final RoleServiceImpl roleServiceImpl;

    @GetMapping("/")
    @PreAuthorize("hasAuthority('SYSTEM_ADMIN')")
    public ResponseEntity<Set<RoleDTO>> findAll() {
        Set<RoleDTO> roles = roleServiceImpl.findAll();

        if (roles.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(roles, HttpStatus.OK);
        }
    }
}
