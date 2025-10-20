package com.sasmitha.lms;

import com.sasmitha.lms.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl {
    private final AuthRepository authRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public void createUser(UserRegisterRequest userRegisterRequest) {
        User user = new User();
        user.setRole(userRegisterRequest.getRole());
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
        System.out.println("User logged in: " + user.getEmail());
        return new LoginResponse("Login successful", "Done");

    }
}
