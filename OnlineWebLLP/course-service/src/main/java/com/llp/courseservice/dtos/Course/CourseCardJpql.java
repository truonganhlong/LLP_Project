package com.llp.courseservice.dtos.Course;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.UUID;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class CourseCardJpql {
    private UUID id;
    private String imageLink;
    private String name;
    private Long createdBy;
    private double rating;
    private int ratingNum;
    private double price;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdAt;
    private int duration;
    private Long topicId;
    private Long subCategoryId;
    private Long levelId;
    private Long languageId;
}
