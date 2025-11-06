package com.sasmitha.lms.dto;

import lombok.*;

import java.time.LocalDateTime;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ModuleDTO {
    private String title;
    private String description;
}
