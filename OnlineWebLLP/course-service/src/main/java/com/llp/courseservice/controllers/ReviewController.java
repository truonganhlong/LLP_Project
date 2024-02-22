package com.llp.courseservice.controllers;

import com.llp.courseservice.services.ReviewService;
import com.llp.sharedproject.exceptions.InternalServerException;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/course/review")
@RequiredArgsConstructor
public class ReviewController {
    private final ReviewService reviewService;

    @Operation(summary = "Api 88: get all prominent reviews in homepage")
    @RequestMapping(value = "/public/allProminentReview", method = RequestMethod.GET)
    public ResponseEntity<?> getAllProminentReview(@RequestParam(defaultValue = "0") Integer pageNo,
                                                   @RequestParam(defaultValue = "4") Integer pageSize){
        try {
            var data = reviewService.getAllProminentReview(pageNo,pageSize);
            return ResponseEntity.ok(data);
        } catch (InternalServerException e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @Operation(summary = "Api 89: get all reviews of course in page course detail when click ")
    @RequestMapping(value = "/public/allReviewByCourse", method = RequestMethod.GET)
    public ResponseEntity<?> getAllReviewByCourse(@RequestParam String courseId,
                                                  @RequestParam(defaultValue = "0") Integer pageNo,
                                                  @RequestParam(defaultValue = "4") Integer pageSize){
        try {
            var data = reviewService.getAllReviewByCourse(courseId,pageNo,pageSize);
            return ResponseEntity.ok(data);
        } catch (InternalServerException e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}
