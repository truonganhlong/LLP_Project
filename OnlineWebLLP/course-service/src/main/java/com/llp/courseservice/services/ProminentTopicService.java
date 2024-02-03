package com.llp.courseservice.services;

import com.llp.courseservice.dtos.ProminentTopic.ProminentTopicAdminResponse;
import com.llp.courseservice.dtos.ProminentTopic.ProminentTopicCreateRequest;
import com.llp.courseservice.dtos.ProminentTopic.ProminentTopicUpdateRequest;
import com.llp.courseservice.dtos.ProminentTopic.ProminentTopicUserResponse;

import java.util.List;

public interface ProminentTopicService {
    List<ProminentTopicAdminResponse> getAllByAdmin();
    List<ProminentTopicUserResponse> getAllByUser(Integer pageNo, Integer pageSize);
    ProminentTopicAdminResponse getById(int id);
    void create(ProminentTopicCreateRequest request);
    void update(int id, ProminentTopicUpdateRequest request);
    void delete(int id);

}
