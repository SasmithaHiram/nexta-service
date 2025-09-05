package com.sasmitha.lms.service.impl;

import com.sasmitha.lms.dto.UserLoginRequest;
import com.sasmitha.lms.dto.UserRegisterRequest;
import com.sasmitha.lms.entity.UserEntity;
import com.sasmitha.lms.repository.UserRepository;
import com.sasmitha.lms.service.UserService;
import com.sasmitha.lms.util.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserEntity register(UserRegisterRequest userRegisterRequest) {
        Optional<UserEntity> existingUser = userRepository.findByEmail(userRegisterRequest.getEmail());

        if (existingUser.isPresent()) {
            throw new RuntimeException("User with email " + userRegisterRequest.getEmail() + " already exists");
        }

        UserEntity userEntity = new UserEntity();
        userEntity.setName(userRegisterRequest.getName());
        userEntity.setEmail(userRegisterRequest.getEmail());
        userEntity.setPassword(passwordEncoder.encode(userRegisterRequest.getPassword()));

        if (userRepository.count() == 0) {
            userEntity.setRole(Role.ADMIN);
        } else {
            userEntity.setRole(Role.STUDENT);
        }
        return userRepository.save(userEntity);
    }

    @Override
    public UserEntity login(UserLoginRequest userLoginRequest) {
        UserEntity user = userRepository.findByEmail(userLoginRequest.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!passwordEncoder.matches(userLoginRequest.getPassword(), user.getPassword())) {
            throw new RuntimeException("Incorrect password");
        }
        return user;
    }
}
