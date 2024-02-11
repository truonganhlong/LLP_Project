package com.llp.userservice.controllers;

import com.llp.sharedproject.exceptions.InternalServerException;
import com.llp.userservice.dtos.registerInstructor.FormRequest;
import com.llp.userservice.entities.User;
import com.llp.userservice.services.RegisterInstructorService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user/registerInstructor")
@RequiredArgsConstructor
@PreAuthorize("hasRole('USER')")
public class RegisterInstructorController {
    private final RegisterInstructorService registerInstructorService;

    @Operation(summary = "Api 79: get all questions from form register instructor")
    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public ResponseEntity<?> getAll(){
        try {
            var data = registerInstructorService.getAllQuestion();
            return ResponseEntity.ok(data);
        } catch (InternalServerException e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @Operation(summary = "Api 80: fill form register instructor for user")
    @RequestMapping(value = "/fill", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> fillForm(Authentication authentication, @RequestBody List<FormRequest> requests){
        try {
            if (authentication != null && authentication.getPrincipal() instanceof User) {
                User user = (User) authentication.getPrincipal();
                int userId = user.getId().intValue();
                registerInstructorService.fillForm(userId, requests);
                return ResponseEntity.status(HttpStatus.CREATED).body("Submit successfully");
            }
            else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
            }
        } catch (InternalServerException e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}
