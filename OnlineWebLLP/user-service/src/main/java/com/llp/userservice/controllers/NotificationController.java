package com.llp.userservice.controllers;

import com.llp.sharedproject.exceptions.InternalServerException;
import com.llp.userservice.entities.User;
import com.llp.userservice.services.NotificationService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user/notification")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class NotificationController {
    private final NotificationService notificationService;

    @Operation(summary = "Api 134: admin send notification to all")
    @RequestMapping(value = "/adminSendToUserAndTeacher", method = RequestMethod.POST)
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> adminSendToUserAndTeacher(Authentication authentication, @RequestParam String forWho, @RequestParam String message){
        try {
            if (authentication != null && authentication.getPrincipal() instanceof User) {
                User user = (User) authentication.getPrincipal();
                int userId = user.getId().intValue();
                notificationService.adminSendToUserAndTeacher(userId, forWho, message);
                return ResponseEntity.status(HttpStatus.CREATED).body("Send notification successfully");
            }
            else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
            }
        } catch (InternalServerException e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @Operation(summary = "Api 135: teacher send notification to their student in their course")
    @RequestMapping(value = "/teacherSendToUser", method = RequestMethod.POST)
    @PreAuthorize("hasRole('TEACHER')")
    public ResponseEntity<?> teacherSendToUser(Authentication authentication, @RequestParam String courseId, @RequestParam String message){
        try {
            if (authentication != null && authentication.getPrincipal() instanceof User) {
                User user = (User) authentication.getPrincipal();
                int userId = user.getId().intValue();
                notificationService.teacherSendToUser(userId, courseId, message);
                return ResponseEntity.status(HttpStatus.CREATED).body("Send notification successfully");
            }
            else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
            }
        } catch (InternalServerException e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @Operation(summary = "Api 136: get notifications of user")
    @RequestMapping(value = "/getYourNotification", method = RequestMethod.GET)
    @PreAuthorize("hasRole('USER') || hasRole('TEACHER') || hasRole('ADMIN')")
    public ResponseEntity<?> getYourNotification(Authentication authentication){
        try {
            if (authentication != null && authentication.getPrincipal() instanceof User) {
                User user = (User) authentication.getPrincipal();
                int userId = user.getId().intValue();
                var data = notificationService.getYourNotification(userId);
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
