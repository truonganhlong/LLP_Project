package com.llp.courseservice.dtos.Level;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class LevelUpdateRequest {
    @JsonProperty(required = true)
    private String name;
}
