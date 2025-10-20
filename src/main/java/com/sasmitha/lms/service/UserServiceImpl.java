package com.sasmitha.lms.service;

import com.sasmitha.lms.repository.AuthRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl {
    private final AuthRepository authRepository;
}
