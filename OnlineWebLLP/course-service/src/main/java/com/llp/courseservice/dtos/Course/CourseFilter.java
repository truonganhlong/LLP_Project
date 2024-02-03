package com.llp.courseservice.dtos.Course;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class CourseFilter {
    @JsonProperty
    private Integer ratingFilter;
    @JsonProperty
    private Integer durationFilter;
    @JsonProperty
    private Integer topicFilter;
    @JsonProperty
    private Integer subCategoryFilter;
    @JsonProperty
    private Integer levelFilter;
    @JsonProperty
    private Integer languageFilter;
    @JsonProperty
    private Integer priceFilter;
}
