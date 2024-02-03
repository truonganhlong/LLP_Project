package com.llp.courseservice.dtos.Topic;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class TopicUpdateRequest {
    @JsonProperty
    private String name;
    @JsonProperty
    private String allAbout;
    @JsonProperty
    private String description;
}
