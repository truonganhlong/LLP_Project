package com.llp.courseservice.services;

import com.llp.courseservice.dtos.Lecture.LectureCreateRequest;
import com.llp.courseservice.dtos.Lecture.LectureDetailAfterPurchasedResponse;
import com.llp.courseservice.dtos.Lecture.LectureDetailBeforePurchasedResponse;
import com.llp.courseservice.dtos.Lecture.LectureStudiedResponse;
import com.llp.courseservice.entities.Section;

import java.util.List;

public interface LectureService {
    void create(LectureCreateRequest request, int sectionId);
    void updateStatus(int id);
    List<LectureDetailBeforePurchasedResponse> getAllLectureBySectionBeforePurchased(int sectionId);
    List<LectureDetailAfterPurchasedResponse> getAllLectureBySectionAfterPurchased(int sectionId);
    LectureDetailAfterPurchasedResponse getById(int id, String authorizationHeader, String courseId);
    List<LectureStudiedResponse> getAllLectureStudied(String authorizationHeader);
}
