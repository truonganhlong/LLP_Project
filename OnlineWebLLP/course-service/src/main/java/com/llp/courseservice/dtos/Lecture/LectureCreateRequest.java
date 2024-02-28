package com.llp.courseservice.dtos.Lecture;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class LectureCreateRequest {
    @JsonProperty(required = true)
    private String name;
    @JsonProperty
    private String goal;
    @JsonProperty(required = true)
    private String link;
    @JsonProperty(required = true)
    private int duration;
    @JsonProperty(required = true)
    private Long createdBy;
    @JsonProperty(required = true)
    private boolean isFree;
}
