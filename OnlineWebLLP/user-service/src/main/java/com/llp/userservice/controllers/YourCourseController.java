package com.llp.userservice.controllers;

import com.llp.sharedproject.exceptions.InternalServerException;
import com.llp.userservice.entities.User;
import com.llp.userservice.services.YourCourseService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user/yourCourse")
@RequiredArgsConstructor
public class YourCourseController {
    private final YourCourseService yourCourseService;

    @Operation(summary = "Api 97: add to your course after checkout")
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> create(Authentication authentication, @RequestParam String courseId, @RequestParam String orderId){
        try {
            if (authentication != null && authentication.getPrincipal() instanceof User) {
                User user = (User) authentication.getPrincipal();
                int userId = user.getId().intValue();
                yourCourseService.create(courseId, userId, orderId);
                return ResponseEntity.status(HttpStatus.CREATED).body("Created successfully");
            }
            else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
            }
        } catch (InternalServerException e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}
