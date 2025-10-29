package com.sasmitha.lms.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "courses")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Module {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;
//    @ManyToOne
//    @JoinColumn(name = "teacher_id", nullable = false)
//    private User teacher;
}
