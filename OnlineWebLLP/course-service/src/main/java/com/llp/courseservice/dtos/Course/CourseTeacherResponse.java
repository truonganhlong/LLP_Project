package com.llp.courseservice.dtos.Course;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class CourseTeacherResponse {
    private UUID id;
    private String name;
    private String description;
    private String overview;
    private List<String> forWho;
    private List<String> requirement;
    private List<String> target;
    private String imageLink;
    private String promoVideoLink;
    private double rating;
    private int ratingNum;
    private int saleNum;
    private double price;
    private String duration;
    private String level;
    private String language;
    private String createdAt;
    private String updatedAt;
}
