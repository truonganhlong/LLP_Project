package com.llp.courseservice.services;

import com.llp.courseservice.dtos.Language.LanguageCreateRequest;
import com.llp.courseservice.dtos.Language.LanguageResponse;
import com.llp.courseservice.dtos.Language.LanguageUpdateRequest;

import java.util.List;

public interface LanguageService {
    List<LanguageResponse> getAll();
    LanguageResponse getById(int id);
    void create(LanguageCreateRequest request);
    void update(int id, LanguageUpdateRequest request);
    void delete(int id);
}
