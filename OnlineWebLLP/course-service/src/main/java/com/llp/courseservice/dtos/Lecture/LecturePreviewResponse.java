package com.llp.courseservice.dtos.Lecture;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class LecturePreviewResponse {
    private Long id;
    private String name;
    private String link;
    private String duration;
}
