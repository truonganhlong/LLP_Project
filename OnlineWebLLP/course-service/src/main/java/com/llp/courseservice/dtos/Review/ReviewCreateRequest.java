package com.llp.courseservice.dtos.Review;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ReviewCreateRequest {
    @JsonProperty(required = true)
    private String courseId;
    @JsonProperty(required = true)
    private String content;
    @JsonProperty(required = true)
    private int rating;
}
