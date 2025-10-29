package com.sasmitha.lms.service;

import com.sasmitha.lms.dto.RoleDTO;
import com.sasmitha.lms.model.Role;
import com.sasmitha.lms.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl {
    private final RoleRepository roleRepository;

    public Set<RoleDTO> findAll() {
        List<Role> rolesFromDatabase = roleRepository.findAll();

        if (rolesFromDatabase.isEmpty()) {
            return Collections.emptySet();
        } else {
            return rolesFromDatabase.stream().map(role -> new RoleDTO(role.getId(), role.getName())).collect(Collectors.toSet());
        }
    }
}
