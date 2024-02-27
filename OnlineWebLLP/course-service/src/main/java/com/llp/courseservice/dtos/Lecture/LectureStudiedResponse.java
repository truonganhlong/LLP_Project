package com.llp.courseservice.dtos.Lecture;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class LectureStudiedResponse {
    private String courseId;
    private String courseName;
    private int lectureId;
    private String lectureName;
    private String link;
}
