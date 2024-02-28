package com.llp.courseservice.controllers;

import com.llp.courseservice.dtos.Review.ReviewCreateRequest;
import com.llp.courseservice.services.ReviewService;
import com.llp.sharedproject.exceptions.InternalServerException;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/course/review")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
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

    @Operation(summary = "Api 102: create review")
    @RequestMapping(value="/user", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> create(@RequestHeader("Authorization") String authorizationHeader, @RequestBody ReviewCreateRequest request){
        try {
            reviewService.create(authorizationHeader,request);
            return ResponseEntity.status(HttpStatus.CREATED).body("Create successfully");
        } catch (InternalServerException e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @Operation(summary = "Api 103: update review to prominent")
    @RequestMapping(value="/admin", method = RequestMethod.PUT)
    public ResponseEntity<?> updateReviewToProminent(@RequestParam String courseId, @RequestParam int userId){
        try {
            reviewService.updateReviewToProminent(courseId,userId);
            return ResponseEntity.status(HttpStatus.CREATED).body("Updated successfully");
        } catch (InternalServerException e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}
