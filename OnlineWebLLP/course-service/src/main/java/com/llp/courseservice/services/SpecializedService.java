package com.llp.courseservice.services;

import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SpecializedService {
    List<Integer> getAllTeacherByCategory(int categoryId);
    List<Integer> getAllTeacherBySubCategory(int subCategoryId);
}
