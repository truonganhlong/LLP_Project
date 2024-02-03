package com.llp.courseservice.dtos.Course;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class CourseCardResponse {
    private UUID id;
    private String imageLink;
    private String name;
    @JsonIgnore
    private int userId;
    private String instructor;
    private double rating;
    private int ratingNum;
    private double price;
    private double discountPrice;
}
