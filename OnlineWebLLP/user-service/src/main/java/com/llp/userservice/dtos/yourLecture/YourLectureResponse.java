package com.llp.userservice.dtos.yourLecture;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class YourLectureResponse {
    private int userId;
    private String courseId;
    private int lectureId;
}
