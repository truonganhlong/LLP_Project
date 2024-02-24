package com.llp.courseservice.dtos.Review;

import lombok.*;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ReviewResponse {
    private UUID courseId;
    private String courseName;
    private int userId;
    private String username;
    private String imageLink;
    private String content;
    private String createdAt;
    private int rating;
}
