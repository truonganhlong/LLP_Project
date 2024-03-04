package com.llp.courseservice.services;


import com.llp.courseservice.dtos.Tag.TagCreateRequest;
import com.llp.courseservice.dtos.Tag.TagResponse;
import com.llp.courseservice.dtos.Tag.TagUpdateRequest;

import java.util.List;
import java.util.UUID;

public interface TagService {
    List<TagResponse> getAll();
    TagResponse getById(int id);
    void create(TagCreateRequest request);
    void update(int id, TagUpdateRequest request);
    void delete(int id);

    void updateBestsellerTag();
    void updateHighestRatedTag();
}
