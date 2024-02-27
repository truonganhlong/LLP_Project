package com.llp.userservice.services;

import com.llp.userservice.dtos.yourLecture.YourLectureResponse;

import java.util.List;

public interface YourLectureService {
    int countByUserAndCourse(int userId, String courseId);
    void create(int userId, String courseId, int lectureId);
    void update(int userId, String courseId, int lectureId);
    List<YourLectureResponse> getAllYourLecture(int userId);
}
