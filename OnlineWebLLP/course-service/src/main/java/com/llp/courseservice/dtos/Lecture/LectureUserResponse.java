package com.llp.courseservice.dtos.Lecture;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class LectureUserResponse {
    private Long id;
    private String name;
    private String goal;
    private String link;
    private int duration;
    private int createdBy;
    private boolean isFree;
}
