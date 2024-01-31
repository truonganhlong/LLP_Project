package com.llp.courseservice.dtos.Topic;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class TopicAdminResponse {
    private Long id;
    private String name;
    private String allAbout;
    private String description;
    private boolean status;
}
