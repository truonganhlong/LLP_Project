package com.llp.courseservice.dtos.Lecture;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder

public class LectureDetailAfterPurchasedResponse {
    private Long id;
    private String name;
    private String goal;
    private String link;
    private int duration;
    private String durationToString;
}
