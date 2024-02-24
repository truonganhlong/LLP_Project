package com.llp.courseservice.services;

import com.llp.courseservice.dtos.Section.SectionCreateRequest;
import com.llp.courseservice.dtos.Section.SectionDetailResponse;
import com.llp.courseservice.entities.Course;

import java.util.List;

public interface SectionService {
    int create(SectionCreateRequest request, String courseId);
    List<SectionDetailResponse> getAllSectionByCourse(String courseId);
}
