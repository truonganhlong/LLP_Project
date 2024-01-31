package com.llp.courseservice.dtos.ProminentTopic;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ProminentTopicAdminResponse {
    private Long id;
    private String title;
    private String overview;
    private Long topicId;
    private String topicName;
}
