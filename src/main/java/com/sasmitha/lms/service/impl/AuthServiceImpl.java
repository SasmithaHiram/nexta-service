package com.sasmitha.lms.service.impl;

import com.sasmitha.lms.config.JWTUtil;
import com.sasmitha.lms.dto.AuthResponse;
import com.sasmitha.lms.dto.UserLoginRequest;
import com.sasmitha.lms.dto.RegisterRequest;
import com.sasmitha.lms.entity.UserEntity;
import com.sasmitha.lms.repository.AuthRepository;
import com.sasmitha.lms.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

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
        return new AuthResponse(token);
    }

    @Override
    public UserEntity instructorRegister(RegisterRequest registerRequest) {
        return null;
//        UserEntity userEntity = new UserEntity();
//        userEntity.setName(userRegisterRequest.getName());
//        userEntity.setEmail(userRegisterRequest.getEmail());
//        userEntity.setPassword(passwordEncoder.encode(userRegisterRequest.getPassword()));
//        userEntity.setRole(Role.INSTRUCTOR);
//        return userRepository.save(userEntity);
    }
}
