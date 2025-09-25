package com.sasmitha.lms.dto;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserRegisterRequest {
    private String username;
    private String email;
    private String password;
}
