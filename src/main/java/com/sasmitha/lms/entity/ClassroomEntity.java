package com.sasmitha.lms.entity;

import com.sasmitha.lms.util.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Entity
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "classrooms")
public class ClassroomEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToOne
    private UserEntity teacher;

    @ManyToMany
    private Set<UserEntity> students;

}
