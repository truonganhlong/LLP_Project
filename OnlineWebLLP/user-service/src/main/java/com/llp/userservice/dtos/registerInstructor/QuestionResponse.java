package com.llp.userservice.dtos.registerInstructor;

import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class QuestionResponse {
    private Long id;
    private String title;
    private String description;
    private String question;
    private List<AnswerResponse> answers;
}
