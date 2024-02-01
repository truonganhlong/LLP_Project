package com.llp.courseservice.services;

import com.llp.courseservice.dtos.Course.CourseOverviewResponse;

import java.util.UUID;

public interface CourseService {
    CourseOverviewResponse getCourseOverview(String id);
}
