package com.sasmitha.lms.service;

import com.sasmitha.lms.entity.UserEntity;

public interface AuthService {
    UserEntity userRegister(RegisterRequest registerRequest);

    AuthResponse login(UserLoginRequest userLoginRequest);
}
