package com.sasmitha.lms.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "courses")
public class CourseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String description;

    @ManyToOne
    @JoinColumn(name = "instructor_id")
    private UserEntity instructor;
}
