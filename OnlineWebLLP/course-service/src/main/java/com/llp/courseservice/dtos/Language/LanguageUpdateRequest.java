package com.llp.courseservice.dtos.Language;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class LanguageUpdateRequest {
    @JsonProperty(required = true)
    private String name;
}
