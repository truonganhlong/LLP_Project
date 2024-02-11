package com.llp.courseservice.services;

import com.llp.courseservice.dtos.Lecture.LectureCreateRequest;
import com.llp.courseservice.entities.Section;

import java.util.List;

public interface LectureService {
    void create(LectureCreateRequest request, int sectionId);
    void updateStatus(int id);
}
