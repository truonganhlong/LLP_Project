package com.llp.commonservice.services;

import com.llp.commonservice.dtos.Intro.IntroCreateRequest;
import com.llp.commonservice.dtos.Intro.IntroResponse;
import com.llp.commonservice.dtos.Intro.IntroUpdateRequest;

import java.util.List;

public interface IntroService {
    List<IntroResponse> getAll();
    List<IntroResponse> getAllByIntroMapId(int introMapId);
    List<IntroResponse> getAllByIntroMapIdByUser(int introMapId);
    IntroResponse getById(int id);
    void create(IntroCreateRequest request);
    void update(int id, IntroUpdateRequest request);
    void updateStatus(int id);
    void delete(int id);
}
