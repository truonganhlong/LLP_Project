package com.llp.userservice.services;

import com.llp.userservice.clients.dtos.CourseCardResponse;

import java.util.List;

public interface YourCourseService {
    void create(String courseId, int userId, String orderId);
    boolean isHaveAuthenticationToCourse(int userId, String courseId);
    List<CourseCardResponse> getYourCourse(int userId);

}
