package com.llp.courseservice.dtos.Lecture;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class LectureDetailBeforePurchasedResponse {
    private Long id;
    private String name;
    private String link;
    private int duration;
    private String durationToString;
    private boolean isFree;
}
