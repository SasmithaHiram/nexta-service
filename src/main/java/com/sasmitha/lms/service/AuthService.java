package com.sasmitha.lms.service;

import com.sasmitha.lms.dto.AuthResponse;
import com.sasmitha.lms.dto.UserLoginRequest;
import com.sasmitha.lms.dto.RegisterRequest;
import com.sasmitha.lms.entity.UserEntity;

public interface AuthService {
    UserEntity userRegister(RegisterRequest registerRequest);

    AuthResponse login(UserLoginRequest userLoginRequest);

    UserEntity instructorRegister(RegisterRequest registerRequest);
}
