package com.llp.courseservice.dtos.Course;

import lombok.*;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class CourseOverviewResponse {
    private UUID id;
    private String name;
    private String updatedAt;
    private int duration;
    private String level;
    private String overview;
    private List<String> tag;
    private List<String> target;
}
