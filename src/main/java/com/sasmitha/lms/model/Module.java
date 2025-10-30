package com.sasmitha.lms.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "modules")
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

    @ManyToMany(mappedBy = "modules")
    private List<User> users;
}
