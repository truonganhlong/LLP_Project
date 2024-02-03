package com.llp.courseservice.dtos.ProminentTopic;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ProminentTopicCreateRequest {
    @JsonProperty(required = true)
    private String title;
    @JsonProperty(required = true)
    private String overview;
    @JsonProperty(required = true)
    private Long topicId;
}
