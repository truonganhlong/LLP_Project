package com.llp.courseservice.services;

import com.llp.courseservice.dtos.Level.LevelCreateRequest;
import com.llp.courseservice.dtos.Level.LevelResponse;
import com.llp.courseservice.dtos.Level.LevelUpdateRequest;

import java.util.List;

public interface LevelService {
    List<LevelResponse> getAll();
    LevelResponse getById(int id);
    void create(LevelCreateRequest request);
    void update(int id, LevelUpdateRequest request);
    void delete(int id);
}
