package com.llp.userservice.mappers;

import com.llp.userservice.dtos.yourLecture.YourLectureResponse;
import com.llp.userservice.entities.YourLecture;

public class YourLectureMapper {
    public static YourLectureResponse convertToResponse(YourLecture yourLecture){
        return YourLectureResponse.builder()
                .userId(yourLecture.getId().getUserId().intValue())
                .courseId(yourLecture.getId().getCourseId())
                .lectureId(yourLecture.getId().getLectureId().intValue())
                .build();
    }
}
