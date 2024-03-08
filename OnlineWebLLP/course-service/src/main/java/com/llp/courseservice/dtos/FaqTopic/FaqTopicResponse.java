package com.llp.courseservice.dtos.FaqTopic;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class FaqTopicResponse {
    private Long id;
    private String question;
    private String answer;
}
