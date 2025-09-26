package com.sasmitha.lms.service.impl;

import com.sasmitha.lms.config.JWTUtil;
import com.sasmitha.lms.dto.AuthResponse;
import com.sasmitha.lms.dto.User;
import com.sasmitha.lms.dto.UserLoginRequest;
import com.sasmitha.lms.dto.RegisterRequest;
import com.sasmitha.lms.entity.UserEntity;
import com.sasmitha.lms.repository.AuthRepository;
import com.sasmitha.lms.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final AuthRepository authRepository;
    private final PasswordEncoder passwordEncoder;
    private final JWTUtil jwtUtil;

    @Override
    public UserEntity userRegister(RegisterRequest registerRequest) {
        if (authRepository.existsByEmail(registerRequest.getEmail())) {
            throw new RuntimeException("User with email " + registerRequest.getEmail() + " already exists");
        }

        UserEntity userEntity = new UserEntity();
        userEntity.setUsername(registerRequest.getUsername());
        userEntity.setEmail(registerRequest.getEmail());
        userEntity.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        userEntity.setRole(registerRequest.getRole());
        return authRepository.save(userEntity);
    }

    @Override
    public AuthResponse login(UserLoginRequest userLoginRequest) {
        UserEntity user = authRepository.findByEmail(userLoginRequest.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!passwordEncoder.matches(userLoginRequest.getPassword(), user.getPassword())) {
            throw new RuntimeException("Incorrect password");
        }

        String token = jwtUtil.generateToken(user.getEmail());
        return new AuthResponse("Bearer", token, user.getRole());
    }

    @Override
    public String resetPassword(String email) {
        return "";
    }

    @Override
    public List<User> users() {
        List<UserEntity> userEntities = authRepository.findAll();

        List<User> users = new ArrayList<>();

        for (UserEntity userEntity: userEntities) {
            User user = new User();
            user.setUsername(userEntity.getUsername());
            user.setEmail(userEntity.getEmail());
            user.setRole(userEntity.getRole());
            users.add(user);
        }
        return users;
    }
}
