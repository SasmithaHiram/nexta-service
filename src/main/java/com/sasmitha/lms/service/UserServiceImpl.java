package com.sasmitha.lms.service;

import com.sasmitha.lms.model.Module;
import com.sasmitha.lms.model.User;
import com.sasmitha.lms.repository.AuthRepository;
import com.sasmitha.lms.repository.ModuleRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class UserServiceImpl {
    private final AuthRepository authRepository;
    private final ModuleRepository moduleRepository;

    public void assignUsersToModule(Long moduleId, List<Long> userIds) {
        Module module = moduleRepository.findById(moduleId)
                .orElseThrow(() -> new RuntimeException("Module not found"));

        List<User> usersById = authRepository.findAllById(userIds);

        if (usersById.isEmpty()) {
            throw new RuntimeException("No users found with given IDs");
        }

        if (module.getUsers()!=null) {
            module.getUsers().addAll(usersById);
        } else {
            module.setUsers(usersById);
        }

        for (User user: usersById) {
            if (user.getModules()!=null) {
                user.getModules().add(module);
            } else {
                user.setModules(List.of(module));
            }
            authRepository.save(user);
        }
        moduleRepository.save(module);
    }

    public List<String> modulesByStudentId(Long studentId) {
        moduleRepository.findModuleByStudentId(studentId);

        if (moduleByStudentId.isEmpty()) {
            return null;
        } else {
            return moduleByStudentId;
        }
    }
}
