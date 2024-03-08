package com.llp.courseservice.services;

import com.llp.courseservice.dtos.FaqTopic.FaqTopicRequest;
import com.llp.courseservice.dtos.FaqTopic.FaqTopicResponse;

import java.util.List;

public interface FaqTopicService {
    List<FaqTopicResponse> getAllByTopic(int topicId);
    void create(FaqTopicRequest request);
    void delete(int id);
}
