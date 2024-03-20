package com.llp.courseservice.services;

import com.llp.courseservice.dtos.VisitCourse.VisitCourseResponse;

public interface VisitCourseService {
    void create(String authorizationHeader, String courseId);
    VisitCourseResponse getAllVisitByCourse(String courseId, String day);
}
