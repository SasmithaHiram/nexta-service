package com.sasmitha.lms.service;

import com.sasmitha.lms.config.JWTUtil;
import com.sasmitha.lms.dto.LoginRequest;
import com.sasmitha.lms.dto.LoginResponse;
import com.sasmitha.lms.dto.UserRegisterRequest;
import com.sasmitha.lms.model.User;
import com.sasmitha.lms.repository.AuthRepository;
import lombok.RequiredArgsConstructor;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl {
    private final AuthRepository authRepository;
    //private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final JWTUtil jwtUtil;

    public void createUser(UserRegisterRequest userRegisterRequest) {
        User user = new User();
        user.setRole(userRegisterRequest.getRole());
        user.setFirstName(userRegisterRequest.getFirstName());
        user.setLastName(userRegisterRequest.getLastName());
        user.setEmail(userRegisterRequest.getEmail());
        user.setPassword(userRegisterRequest.getPassword());
        authRepository.save(user);
    }

    public LoginResponse loginUser(LoginRequest loginRequest) {
        User user = authRepository.findByEmail(loginRequest.getEmail()).orElse(null);

        if (user == null) {
            return new LoginResponse("User not found", null);
        }

        if (!loginRequest.getPassword().equals(user.getPassword())) {
            return new LoginResponse("Invalid password", null);
        }
        String token = jwtUtil.generateToken(loginRequest.getEmail(), user.getRole().name());
        return new LoginResponse("Login successful", token);

    }
}
