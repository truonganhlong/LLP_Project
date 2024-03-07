package com.llp.userservice.controllers;

import com.llp.sharedproject.exceptions.BadRequestException;
import com.llp.sharedproject.exceptions.ConflictException;
import com.llp.sharedproject.exceptions.InternalServerException;
import com.llp.sharedproject.exceptions.NotFoundException;
import com.llp.userservice.dtos.user.AuthenticationRequest;
import com.llp.userservice.dtos.user.RegisterGoogleRequest;
import com.llp.userservice.dtos.user.RegisterRequest;
import com.llp.userservice.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user/auth")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class AuthController {
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

    @Operation(summary = "Api 81: register as admin")
    @RequestMapping(value = "/registerAsAdmin", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> registerAsAdmin(@RequestBody RegisterRequest request){
        try {
            var data = userService.registerAsAdmin(request);
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
        } catch (NotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (BadCredentialsException e){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        } catch (BadRequestException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (InternalServerException e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @Operation(summary = "Api 125: forget password in login form, send an email to get otp for verifying")
    @RequestMapping(value = "/forgetPassword", method = RequestMethod.POST)
    public ResponseEntity<?> forgetPassword(@RequestParam String email){
        try {
            userService.forgetPassword(email);
            return ResponseEntity.status(HttpStatus.CREATED).body("Send successfully, please check your gmail");
        } catch (NotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (InternalServerException e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @Operation(summary = "Api 127: login via google")
    @RequestMapping(value = "/loginViaGoogle", method = RequestMethod.POST)
    public ResponseEntity<?> loginViaGoogle(@RequestBody RegisterGoogleRequest request){
        try {
            userService.loginViaGoogle(request);
            return ResponseEntity.status(HttpStatus.CREATED).body("Login successfully");
        } catch (NotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (InternalServerException e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}
