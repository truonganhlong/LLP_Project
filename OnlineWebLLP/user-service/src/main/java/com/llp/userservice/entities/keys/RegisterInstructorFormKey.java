package com.llp.userservice.entities.keys;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Embeddable
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RegisterInstructorFormKey implements Serializable {
    @Column(name = "userId")
    private Long userId;
    @Column(name = "questionId")
    private Long questionId;
    @Column(name = "answerId")
    private Long answerId;
}
