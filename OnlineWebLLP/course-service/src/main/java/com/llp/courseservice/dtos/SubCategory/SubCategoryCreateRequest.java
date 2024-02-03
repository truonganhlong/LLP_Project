package com.llp.courseservice.dtos.SubCategory;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class SubCategoryCreateRequest {
    @JsonProperty(required = true)
    private String name;
    @JsonProperty(required = true)
    private int categoryId;
}
