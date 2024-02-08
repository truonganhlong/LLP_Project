package com.llp.courseservice.dtos.Section;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.llp.courseservice.dtos.Lecture.LectureCreateRequest;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class SectionCreateRequest {
    @JsonProperty(required = true)
    private String name;
//    @JsonProperty(required = true)
//    private List<LectureCreateRequest> lectureRequest;
}
