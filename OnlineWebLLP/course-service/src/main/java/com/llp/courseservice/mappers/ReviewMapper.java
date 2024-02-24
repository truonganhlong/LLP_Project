package com.llp.courseservice.mappers;

import com.llp.courseservice.dtos.Review.ReviewResponse;
import com.llp.courseservice.entities.Review;
import com.llp.courseservice.repositories.ReviewRepository;

import java.time.format.DateTimeFormatter;

public class ReviewMapper {
    public static ReviewResponse convertToResponse(ReviewRepository.ReviewData review){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMMM yyyy hh:mm");
        String createdAt = review.getCreatedAt().format(formatter);
        return ReviewResponse.builder()
                .courseId(review.getCourseId())
                .courseName(review.getName())
                .userId(review.getUserId().intValue())
                .content(review.getContent())
                .rating(review.getRating())
                .createdAt(createdAt)
                .build();
    }
}
