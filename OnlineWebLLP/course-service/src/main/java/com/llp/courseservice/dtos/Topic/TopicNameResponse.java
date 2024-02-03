package com.llp.courseservice.dtos.Topic;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class TopicNameResponse {
    private Long id;
    private String name;
}
