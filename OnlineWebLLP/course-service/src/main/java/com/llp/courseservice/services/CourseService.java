package com.llp.courseservice.services;

import com.llp.courseservice.dtos.Course.*;
import com.llp.courseservice.dtos.Section.SectionCreateRequest;

import java.util.List;
import java.util.UUID;

public interface CourseService {
    CourseOverviewResponse getCourseOverview(String id);

    List<CourseCardResponse> getAllProminentCourse(Integer pageNo, Integer pageSize);
    List<CourseCardResponse> getAllProminentCourseByTopicId(int topicId, Integer pageNo, Integer pageSize);
    List<CourseCardResponse> getAllProminentCourseBySubCategoryId(int subCategoryId, Integer pageNo, Integer pageSize);

    List<CourseCardResponse> getAllProminentCourseByCategoryId(int categoryId, Integer pageNo, Integer pageSize);
    List<CourseCardResponse> getAllCourseByTopicId(int topicId, Integer pageNo, Integer pageSize, String sortBy, CourseFilter filter);
    List<CourseCardResponse> getAllCourseBySubCategoryId(int subCategoryId, Integer pageNo, Integer pageSize, String sortBy, CourseFilter filter);
    List<CourseCardResponse> getAllCourseByCategoryId(int categoryId, Integer pageNo, Integer pageSize, String sortBy, CourseFilter filter);
    void updateToProminent(String id);
    void updateStatus(String id);
    void delete(String id);
    List<CourseCardResponse> searchCourseByName(String keyword, Integer pageNo, Integer pageSize, String sortBy, CourseFilter filter);
    String create(CourseCreateRequest request);
    //void updateDuration(String id, int duration);

    List<CourseTeacherResponse> getByTeacher(int createdBy);

    CourseCardResponse getCourseCardById(String courseId);

    CourseDetailResponse getCourseDetail(String courseId, String authorizationHeader);
    void updateSaleNum(String courseId);
    List<CourseCardResponse> getByCategoryByLastViewCourse(String authorizationHeader, Integer pageNo, Integer pageSize);
    List<CourseCardResponse> getBySubCategoryByLastViewCourse(String authorizationHeader, Integer pageNo, Integer pageSize);
    List<CourseCardResponse> getByTopicByLastViewCourse(String authorizationHeader, Integer pageNo, Integer pageSize);
    List<CourseCardResponse> getAllCourseByCreatedBy(int createdBy, Integer pageNo, Integer pageSize);
    CoursePreviewResponse getAllCoursePreview(String courseId);

}
