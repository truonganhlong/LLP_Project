package com.llp.courseservice.dtos.Lecture;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class LectureAdminResponse {
    private Long id;
    private String name;
    private String goal;
    private String link;
    private int duration;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdAt;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updatedAt;
    private int createdBy;
    private int updatedBy;
    private boolean isFree;
    private boolean status;
}
