package com.llp.courseservice.services;

import com.llp.courseservice.dtos.Category.*;

import java.util.List;

public interface CategoryService {
    List<CategoryAdminResponse> getAll();
    List<CategoryUserResponse> getAllByUser();
    List<CategoryNameResponse> getNameByUser();
    CategoryAdminResponse getById(int id);
    void create(CategoryCreateRequest request);
    void update(int id, CategoryUpdateRequest request);
    void updateStatus(int id);
    void delete(int id);
}
