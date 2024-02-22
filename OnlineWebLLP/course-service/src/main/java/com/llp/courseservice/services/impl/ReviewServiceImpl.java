package com.llp.courseservice.services.impl;

import com.llp.courseservice.clients.UserClient;
import com.llp.courseservice.dtos.Review.ReviewResponse;
import com.llp.courseservice.mappers.ReviewMapper;
import com.llp.courseservice.repositories.ReviewRepository;
import com.llp.courseservice.services.ReviewService;
import com.llp.sharedproject.exceptions.InternalServerException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {
    private final ReviewRepository reviewRepository;
    private final UserClient userClient;

    @Override
    public List<ReviewResponse> getAllProminentReview(Integer pageNo, Integer pageSize) {
        try {
            Pageable paging = PageRequest.of(pageNo, pageSize);
            List<ReviewRepository.ReviewData> reviews = reviewRepository.getAllProminentReview(paging);
            List<ReviewResponse> data = reviews.stream().map(ReviewMapper::convertToResponse).collect(Collectors.toList());
            for (var review: data) {
                review.setUsername(userClient.getUserInformation(review.getUserId()).getFullname());
                review.setImageLink(userClient.getUserInformation(review.getUserId()).getImageLink());
            }
            return data;
        } catch (Exception e){
            throw new InternalServerException("Server Error");
        }
    }

    @Override
    public List<ReviewResponse> getAllReviewByCourse(String courseId, Integer pageNo, Integer pageSize) {
        try {
            Pageable paging = PageRequest.of(pageNo, pageSize);
            List<ReviewRepository.ReviewData> reviews = reviewRepository.getAllReviewByCourse(courseId,paging);
            List<ReviewResponse> data = reviews.stream().map(ReviewMapper::convertToResponse).collect(Collectors.toList());
            for (var review: data) {
                review.setUsername(userClient.getUserInformation(review.getUserId()).getFullname());
                review.setImageLink(userClient.getUserInformation(review.getUserId()).getImageLink());
            }
            return data;
        } catch (Exception e){
            throw new InternalServerException("Server Error");
        }
    }
}
