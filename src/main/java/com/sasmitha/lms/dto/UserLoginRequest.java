package com.sasmitha.lms.dto;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserLoginRequest {
    private String email;
    private String password;
}
