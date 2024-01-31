package com.llp.courseservice.dtos.ProminentTopic;

import com.llp.courseservice.dtos.Course.CourseCardResponse;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ProminentTopicUserResponse {
    private Long id;
    private String title;
    private String overview;
    private Long topicId;
    private String topicName;
    private List<CourseCardResponse> courseCardResponses;
}
