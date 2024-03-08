package com.llp.courseservice.dtos.FaqTopic;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class FaqTopicRequest {
    @JsonProperty(required = true)
    private String question;
    @JsonProperty(required = true)
    private String answer;
    @JsonProperty(required = true)
    private int topicId;
}
