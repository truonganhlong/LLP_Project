package com.llp.courseservice.dtos.SubCategory;

import com.llp.courseservice.dtos.Topic.TopicNameResponse;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class SubCategoryToTopicResponse {
    private Long id;
    private String name;
    private List<TopicNameResponse> topics;
}
