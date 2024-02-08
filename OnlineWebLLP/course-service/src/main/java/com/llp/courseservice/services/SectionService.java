package com.llp.courseservice.services;

import com.llp.courseservice.dtos.Section.SectionCreateRequest;
import com.llp.courseservice.entities.Course;

import java.util.List;

public interface SectionService {
    void create(SectionCreateRequest request, Course course);
}
