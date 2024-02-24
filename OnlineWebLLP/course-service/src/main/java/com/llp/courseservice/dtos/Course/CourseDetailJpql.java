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
public class CourseDetailJpql {
    private UUID id;
    private String name;
    private String description;
    private String overview;
    private String forWho;
    private String requirement;
    private String target;
    private String imageLink;
    private String promoVideoLink;
    private double rating;
    private int ratingNum;
    private int saleNum;
    private double price;
    private int duration;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdAt;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updatedAt;
    private Long createdBy;
    private Long topicId;
    private Long subCategoryId;
    private Long categoryId;
    private Long levelId;
    private Long languageId;
}
