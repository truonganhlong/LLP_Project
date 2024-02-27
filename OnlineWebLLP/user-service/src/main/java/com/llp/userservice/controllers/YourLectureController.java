package com.llp.userservice.controllers;

import com.llp.sharedproject.exceptions.InternalServerException;
import com.llp.userservice.entities.User;
import com.llp.userservice.services.YourLectureService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user/yourLecture")
@RequiredArgsConstructor
@PreAuthorize("hasRole('USER')")
public class YourLectureController {
    private final YourLectureService yourLectureService;
    @Operation(summary = "Api 112: return count of your lecture table by userId and courseId")
    @RequestMapping(value="/countByUserAndCourse", method = RequestMethod.GET)
    public ResponseEntity<?> countByUserAndCourse(Authentication authentication, @RequestParam String courseId){
        try {
            if (authentication != null && authentication.getPrincipal() instanceof User) {
                User user = (User) authentication.getPrincipal();
                int userId = user.getId().intValue();
                var data = yourLectureService.countByUserAndCourse(userId, courseId);
                return ResponseEntity.ok(data);
            }
            else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
            }
        } catch (InternalServerException e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @Operation(summary = "Api 113: create your lecture")
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> create(Authentication authentication, @RequestParam String courseId, @RequestParam int lectureId){
        try {
            if (authentication != null && authentication.getPrincipal() instanceof User) {
                User user = (User) authentication.getPrincipal();
                int userId = user.getId().intValue();
                yourLectureService.create(userId, courseId, lectureId);
                return ResponseEntity.status(HttpStatus.CREATED).body("Created successfully");
            }
            else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
            }
        } catch (InternalServerException e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @Operation(summary = "Api 114: update your lecture")
    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity<?> update(Authentication authentication, @RequestParam String courseId, @RequestParam int lectureId){
        try {
            if (authentication != null && authentication.getPrincipal() instanceof User) {
                User user = (User) authentication.getPrincipal();
                int userId = user.getId().intValue();
                yourLectureService.update(userId, courseId, lectureId);
                return ResponseEntity.status(HttpStatus.CREATED).body("Created successfully");
            }
            else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
            }
        } catch (InternalServerException e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @Operation(summary = "Api 115: return your lecture by user")
    @RequestMapping(value="/getAllYourLecture", method = RequestMethod.GET)
    public ResponseEntity<?> getAllYourLecture(Authentication authentication){
        try {
            if (authentication != null && authentication.getPrincipal() instanceof User) {
                User user = (User) authentication.getPrincipal();
                int userId = user.getId().intValue();
                var data = yourLectureService.getAllYourLecture(userId);
                return ResponseEntity.ok(data);
            }
            else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
            }
        } catch (InternalServerException e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}
