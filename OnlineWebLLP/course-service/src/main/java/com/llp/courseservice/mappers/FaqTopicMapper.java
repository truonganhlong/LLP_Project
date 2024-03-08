package com.llp.courseservice.mappers;

import com.llp.courseservice.dtos.FaqTopic.FaqTopicResponse;
import com.llp.courseservice.entities.FaqTopic;

public class FaqTopicMapper {
    public static FaqTopicResponse convertToResponse(FaqTopic faqTopic){
        return FaqTopicResponse.builder()
                .id(faqTopic.getId())
                .question(faqTopic.getQuestion())
                .answer(faqTopic.getAnswer())
                .build();
    }
}
