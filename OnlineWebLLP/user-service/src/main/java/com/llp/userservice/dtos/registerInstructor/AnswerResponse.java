package com.llp.userservice.dtos.registerInstructor;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class AnswerResponse {
    private Long id;
    private String answer;
}
