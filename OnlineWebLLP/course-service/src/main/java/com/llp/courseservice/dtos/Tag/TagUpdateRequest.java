package com.llp.courseservice.dtos.Tag;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class TagUpdateRequest {
    @JsonProperty(required = true)
    private String name;
}
