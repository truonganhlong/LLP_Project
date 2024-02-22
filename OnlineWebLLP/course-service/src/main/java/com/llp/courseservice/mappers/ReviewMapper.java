package com.llp.courseservice.mappers;

import com.llp.courseservice.dtos.Review.ReviewResponse;
import com.llp.courseservice.entities.Review;
import com.llp.courseservice.repositories.ReviewRepository;

public class ReviewMapper {
    public static ReviewResponse convertToResponse(ReviewRepository.ReviewData review){
        return ReviewResponse.builder()
                .courseId(review.getCourseId())
                .courseName(review.getName())
                .userId(review.getUserId().intValue())
                .content(review.getContent())
                .rating(review.getRating())
                .build();
    }
}
