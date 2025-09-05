package com.sasmitha.lms.service;

import com.sasmitha.lms.dto.UserLoginRequest;
import com.sasmitha.lms.dto.UserRegisterRequest;
import com.sasmitha.lms.entity.UserEntity;

public interface UserService {
    UserEntity register(UserRegisterRequest userRegisterRequest);

    UserEntity login(UserLoginRequest userLoginRequest);
}
