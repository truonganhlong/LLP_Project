package com.llp.userservice.controllers;

import com.llp.sharedproject.exceptions.ConflictException;
import com.llp.sharedproject.exceptions.InternalServerException;
import com.llp.sharedproject.exceptions.NotFoundException;
import com.llp.userservice.dtos.user.AuthenticationRequest;
import com.llp.userservice.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.llp.userservice.dtos.user.RegisterRequest;


@RestController
@RequestMapping("/api/user/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    @Operation(summary = "Api 72: register")
    @RequestMapping(value = "/register", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> register(@RequestBody RegisterRequest request){
        try {
            var data = userService.register(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(data);
        } catch (ConflictException e){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        } catch (InternalServerException e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
    @Operation(summary = "Api 73: login via form")
    @RequestMapping(value = "/loginViaForm", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> loginViaForm(@RequestBody AuthenticationRequest request){
        try {
            var data = userService.loginViaForm(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(data);
        } catch (InternalServerException e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}
