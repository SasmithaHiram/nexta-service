package com.sasmitha.lms.controller;

import com.sasmitha.lms.dto.ModuleDTO;
import com.sasmitha.lms.service.ModuleServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/modules")
@RequiredArgsConstructor
@CrossOrigin
public class ModuleController {
    private final ModuleServiceImpl moduleServiceImpl;

//    @PostMapping
//    @PreAuthorize("hasAuthority('SYSTEM_ADMIN')")
//    public ResponseEntity<ModuleDTO>

    @GetMapping
    @PreAuthorize("hasAuthority('SYSTEM_ADMIN')")
    public ResponseEntity<List<ModuleDTO>> getAll() {
        List<ModuleDTO> modules = moduleServiceImpl.getAll();

        if (modules.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(modules);
        }
    }
}
