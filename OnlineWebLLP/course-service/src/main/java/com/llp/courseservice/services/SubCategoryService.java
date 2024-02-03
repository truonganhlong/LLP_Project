package com.llp.courseservice.services;

import com.llp.courseservice.dtos.SubCategory.SubCategoryCreateRequest;
import com.llp.courseservice.dtos.SubCategory.SubCategoryAdminResponse;
import com.llp.courseservice.dtos.SubCategory.SubCategoryUpdateRequest;
import com.llp.courseservice.dtos.SubCategory.SubCategoryUserResponse;

import java.util.List;

public interface SubCategoryService {
    List<SubCategoryAdminResponse> getAll();
    List<SubCategoryAdminResponse> getByAdminFilterByCategory(int categoryId);
    List<SubCategoryUserResponse> getByUserFilterByCategory(int categoryId);
    SubCategoryAdminResponse getById(int id);
    void create(SubCategoryCreateRequest request);
    void update(int id, SubCategoryUpdateRequest request);
    void updateStatus(int id);
    void delete(int id);
}
