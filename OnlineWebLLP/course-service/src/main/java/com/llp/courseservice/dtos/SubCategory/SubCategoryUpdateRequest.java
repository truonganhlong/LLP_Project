package com.llp.courseservice.dtos.SubCategory;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class SubCategoryUpdateRequest {
    @JsonProperty
    private String name;
}
