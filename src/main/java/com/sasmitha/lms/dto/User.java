package com.sasmitha.lms.dto;

import com.sasmitha.lms.util.Role;
import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class User {
    private String username;
    private String email;
    private Role role;
}
