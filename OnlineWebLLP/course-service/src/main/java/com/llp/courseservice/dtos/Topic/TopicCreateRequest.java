package com.llp.courseservice.dtos.Topic;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class TopicCreateRequest {
    @JsonProperty(required = true)
    private String name;
    @JsonProperty(required = true)
    private String allAbout;
    @JsonProperty(required = true)
    private String description;
    @JsonProperty(required = true)
    private int subCategoryId;
}
