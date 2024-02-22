package com.llp.courseservice.services;


import com.llp.courseservice.dtos.Review.ReviewResponse;

import java.util.List;

public interface ReviewService {
    List<ReviewResponse> getAllProminentReview(Integer pageNo, Integer pageSize);

    List<ReviewResponse> getAllReviewByCourse(String courseId, Integer pageNo, Integer pageSize);


}
