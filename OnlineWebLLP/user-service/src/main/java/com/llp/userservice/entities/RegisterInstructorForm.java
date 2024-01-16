package com.llp.userservice.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.llp.userservice.entities.keys.RegisterInstructorFormKey;
import jakarta.persistence.*;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class RegisterInstructorForm {
    @EmbeddedId
    private RegisterInstructorFormKey id;
    @ManyToOne
    @MapsId("userId")
    @JoinColumn(name = "userId")
    @JsonIgnore
    private User user;
    @ManyToOne
    @MapsId("questionId")
    @JoinColumn(name = "questionId")
    @JsonIgnore
    private Question question;
    @ManyToOne
    @MapsId("answerId")
    @JoinColumn(name = "answerId")
    @JsonIgnore
    private Answer answer;
}
