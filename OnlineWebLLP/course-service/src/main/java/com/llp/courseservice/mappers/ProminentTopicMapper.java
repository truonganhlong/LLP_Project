package com.llp.courseservice.mappers;

import com.llp.courseservice.dtos.ProminentTopic.ProminentTopicAdminResponse;
import com.llp.courseservice.dtos.ProminentTopic.ProminentTopicUserResponse;
import com.llp.courseservice.entities.ProminentTopic;

public class ProminentTopicMapper {
    public static ProminentTopicAdminResponse convertToAdminResponse(ProminentTopic prominentTopic){
        return ProminentTopicAdminResponse.builder()
                .id(prominentTopic.getId())
                .title(prominentTopic.getTitle())
                .overview(prominentTopic.getOverview())
                .topicId(prominentTopic.getTopic().getId())
                .topicName(prominentTopic.getTopic().getName())
                .build();
    }

    public static ProminentTopicUserResponse convertToUserResponse(ProminentTopic prominentTopic){
        return ProminentTopicUserResponse.builder()
                .id(prominentTopic.getId())
                .title(prominentTopic.getTitle())
                .overview(prominentTopic.getOverview())
                .topicId(prominentTopic.getTopic().getId())
                .topicName(prominentTopic.getTopic().getName())
                .build();
    }
}
