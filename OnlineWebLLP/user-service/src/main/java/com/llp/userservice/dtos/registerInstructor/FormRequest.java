package com.llp.userservice.dtos.registerInstructor;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class FormRequest {
    @JsonProperty(required = true)
    private int questionId;
    @JsonProperty(required = true)
    private int answerId;
}
