package com.llp.userservice.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Table(name = "registerInstructorQuestion")
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;
    private String question;
    @OneToMany(mappedBy = "question")
    private List<RegisterInstructorForm> registerInstructorForms;
    @OneToMany(mappedBy = "question")
    private List<Answer> answers;
}
