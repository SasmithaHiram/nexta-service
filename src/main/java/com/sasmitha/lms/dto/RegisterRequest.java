package com.sasmitha.lms.dto;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class RegisterRequest {
    private String username;
    private String email;
    private String password;
}
