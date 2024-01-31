package com.llp.courseservice.dtos.ProminentTopic;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ProminentTopicUpdateRequest {
    @JsonProperty()
    private String title;
    @JsonProperty()
    private String overview;
    @JsonProperty()
    private Long topicId;
}
