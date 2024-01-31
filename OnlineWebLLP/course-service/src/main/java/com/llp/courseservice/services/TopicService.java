package com.llp.courseservice.services;



import com.llp.courseservice.dtos.Topic.TopicAdminResponse;
import com.llp.courseservice.dtos.Topic.TopicCreateRequest;
import com.llp.courseservice.dtos.Topic.TopicNameResponse;
import com.llp.courseservice.dtos.Topic.TopicUpdateRequest;

import java.util.List;

public interface TopicService {
    List<TopicAdminResponse> getAll();
    List<TopicAdminResponse> getByAdminFilterBySubCategory(int subCategoryId);
    List<TopicNameResponse> getByUserFilterBySubCategory(int subCategoryId);
    TopicAdminResponse getById(int id);
    void create(TopicCreateRequest request);
    void update(int id, TopicUpdateRequest request);
    void updateStatus(int id);
    void delete(int id);
}
