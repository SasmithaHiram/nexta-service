package com.sasmitha.lms.dto;

import com.sasmitha.lms.util.Role;
import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserRegisterRequest {
    private Role role;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
}
