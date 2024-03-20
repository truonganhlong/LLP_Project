package com.llp.courseservice.dtos.VisitCourse;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class VisitCourseResponse {
    private int anonymousVisit;
    private int userVisit;
    private int totalVisit;
}
