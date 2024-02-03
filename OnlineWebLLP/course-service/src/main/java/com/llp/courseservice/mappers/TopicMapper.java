package com.llp.courseservice.mappers;


import com.llp.courseservice.dtos.Topic.TopicAdminResponse;
import com.llp.courseservice.dtos.Topic.TopicNameResponse;
import com.llp.courseservice.entities.Topic;
import com.llp.courseservice.repositories.TopicRepository;

public class TopicMapper {
    public static TopicAdminResponse convertToAdminResponse(Topic topic){
        return  TopicAdminResponse.builder()
                .id(topic.getId())
                .name(topic.getName())
                .allAbout(topic.getAllAbout())
                .description(topic.getDescription())
                .status(topic.isStatus())
                .build();
    }

    public static TopicNameResponse convertToUserResponse(TopicRepository.TopicByName topic){
        return  TopicNameResponse.builder()
                .id(topic.getId())
                .name(topic.getName())
                .build();
    }
}
