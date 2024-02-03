package com.llp.userservice.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Table(name = "registerInstructorAnswer")
public class Answer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String answer;
    @ManyToOne
    @JoinColumn(name = "questionId")
    @JsonIgnore
    private Question question;
    @OneToMany(mappedBy = "answer")
    private List<RegisterInstructorForm> registerInstructorForms;
}
