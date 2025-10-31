package com.sasmitha.lms.service;

import com.sasmitha.lms.config.JWTUtil;
import com.sasmitha.lms.dto.LoginRequest;
import com.sasmitha.lms.dto.LoginResponse;
import com.sasmitha.lms.dto.UserRegisterRequest;
import com.sasmitha.lms.model.Role;
import com.sasmitha.lms.model.User;
import com.sasmitha.lms.repository.AuthRepository;
import com.sasmitha.lms.repository.RoleRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl {
    private final AuthRepository authRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final JWTUtil jwtUtil;
    private final RoleRepository roleRepository;

    public void createUser(UserRegisterRequest userRegisterRequest) {
        Role roleFromDB = roleRepository.findByName(userRegisterRequest.getRole())
                .orElseThrow(() -> new RuntimeException("Role not found"));

        User user = new User();
        Role role = new Role();
        role.setName(roleFromDB.getName());
        user.setFirstName(userRegisterRequest.getFirstName());
        user.setLastName(userRegisterRequest.getLastName());
        user.setEmail(userRegisterRequest.getEmail());
        user.setPassword(bCryptPasswordEncoder.encode(userRegisterRequest.getPassword()));
        authRepository.save(user);
    }

    public LoginResponse loginUser(LoginRequest loginRequest) {
        User user = authRepository.findByEmail(loginRequest.getEmail()).orElse(null);

        if (user == null) {
            return new LoginResponse("User not found", null);
        }

        if (!bCryptPasswordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
            return new LoginResponse("Invalid password", null);
        }
        String token = jwtUtil.generateToken(loginRequest.getEmail(), user.getRole().getName());
        return new LoginResponse("Login successful", token);

    }
}
