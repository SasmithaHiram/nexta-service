package com.sasmitha.lms;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class LoginResponse {
    private String email;
    private String token;
}
