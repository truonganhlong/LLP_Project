package com.llp.userservice.controllers;

import com.llp.sharedproject.exceptions.InternalServerException;
import com.llp.sharedproject.exceptions.NotFoundException;
import com.llp.userservice.dtos.user.UserUpdateRequest;
import com.llp.userservice.entities.User;
import com.llp.userservice.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


@RestController
@RequestMapping("/api/user/user")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {
    private final UserService userService;

    @Operation(summary = "Api 76: get user information")
    @RequestMapping(value = "/information", method = RequestMethod.GET)
    @PreAuthorize("hasRole('USER') || hasRole('TEACHER') || hasRole('ADMIN')")
    public ResponseEntity<?> getUserInformation(Authentication authentication){
        try {
            if (authentication != null && authentication.getPrincipal() instanceof User) {
                User user = (User) authentication.getPrincipal();

                // Access the details, including "username"
                String username = user.getUsername(); // Assuming the "username" is stored in the username field
                var data = userService.getUserInformation(username);
                return ResponseEntity.ok(data);
            }
            else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
            }
        } catch (NotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (InternalServerException e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @Operation(summary = "Api 77: update user information")
    @RequestMapping(value = "/information", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('USER') || hasRole('TEACHER') || hasRole('ADMIN')")
    public ResponseEntity<?> updateInformation(Authentication authentication, @RequestBody UserUpdateRequest request){
        try {
            if (authentication != null && authentication.getPrincipal() instanceof User) {
                User user = (User) authentication.getPrincipal();

                // Access the details, including "username"
                String username = user.getUsername(); // Assuming the "username" is stored in the username field
                userService.updateInformation(username, request);
                return ResponseEntity.status(HttpStatus.CREATED).body("Updated successfully");
            }
            else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
            }
        } catch (NotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (InternalServerException e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @Operation(summary = "Api 78: update avatar")
    @RequestMapping(value = "/avatar", method = RequestMethod.PUT, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @PreAuthorize("hasRole('USER') || hasRole('TEACHER') || hasRole('ADMIN')")
    public ResponseEntity<?> updateAvatar(Authentication authentication, @RequestBody MultipartFile avatar){
        try {
            if (authentication != null && authentication.getPrincipal() instanceof User) {
                User user = (User) authentication.getPrincipal();

                // Access the details, including "username"
                String username = user.getUsername(); // Assuming the "username" is stored in the username field
                userService.updateAvatar(username, avatar);
                return ResponseEntity.status(HttpStatus.CREATED).body("Updated successfully");
            }
            else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
            }
        } catch (NotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (InternalServerException e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @Operation(summary = "Api 84: return your account userId for other services")
    @RequestMapping(value = "/returnUserId", method = RequestMethod.GET)
    @PreAuthorize("hasRole('USER') || hasRole('TEACHER') || hasRole('ADMIN')")
    public ResponseEntity<?> returnUserId(Authentication authentication){
        try {
            if (authentication != null && authentication.getPrincipal() instanceof User) {
                User user = (User) authentication.getPrincipal();
                int userId = user.getId().intValue();
                return ResponseEntity.ok(userId);
            }
            else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
            }
        } catch (InternalServerException e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @Operation(summary = "Api 86: get instructor information")
    @RequestMapping(value = "/public/instructorInformation/{userId}", method = RequestMethod.GET)
    @PreAuthorize("permitAll()")
    public ResponseEntity<?> getInstructorInformation(@PathVariable int userId){
        try {
            var data = userService.getInstructorInformation(userId);
            return ResponseEntity.ok(data);
        } catch (NotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (InternalServerException e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @Operation(summary = "Api 87: get other user information")
    @RequestMapping(value = "/public/userInformation/{userId}", method = RequestMethod.GET)
    @PreAuthorize("permitAll()")
    public ResponseEntity<?> getOtherUserInformation(@PathVariable int userId){
        try {
            var data = userService.getOtherUserInformation(userId);
            return ResponseEntity.ok(data);
        } catch (NotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (InternalServerException e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}
