package com.llp.courseservice.dtos.Course;

import com.llp.courseservice.dtos.Lecture.LecturePreviewResponse;
import lombok.*;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class CoursePreviewResponse {
    private UUID id;
    private String name;
    private List<LecturePreviewResponse> lectures;
}
