package com.sasmitha.lms.service.impl;

import com.sasmitha.lms.dto.UserRegisterRequest;
import com.sasmitha.lms.entity.UserEntity;
import com.sasmitha.lms.repository.UserRepository;
import com.sasmitha.lms.service.UserService;
import com.sasmitha.lms.util.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
//    private final PasswordEncoder passwordEncoder;

    @Override
    public UserEntity register(UserRegisterRequest userRegisterRequest) {
        UserEntity userEntity = new UserEntity();
        userEntity.setName(userRegisterRequest.getName());
        userEntity.setEmail(userRegisterRequest.getEmail());
        userEntity.setPassword(userRegisterRequest.getPassword());

        if (userRepository.count() == 0) {
            userEntity.setRole(Role.ADMIN);
        } else {
            userEntity.setRole(Role.STUDENT);
        }
        return userRepository.save(userEntity);
    }
}
