package com.llp.userservice.controllers;

import com.llp.sharedproject.exceptions.InternalServerException;
import com.llp.userservice.entities.User;
import com.llp.userservice.services.MessageService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user/message")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class MessageController {
    private final MessageService messageService;

    @Operation(summary = "Api 131: send message")
    @RequestMapping(value = "/send", method = RequestMethod.POST)
    @PreAuthorize("hasRole('USER') || hasRole('TEACHER') || hasRole('ADMIN')")
    public ResponseEntity<?> sendMessage(Authentication authentication,@RequestParam String to,@RequestParam String content){
        try {
            if (authentication != null && authentication.getPrincipal() instanceof User) {
                User user = (User) authentication.getPrincipal();
                int userId = user.getId().intValue();
                messageService.send(userId, to, content);
                return ResponseEntity.status(HttpStatus.CREATED).body("Send message");
            }
            else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
            }
        } catch (InternalServerException e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @Operation(summary = "Api 132: get message with other user")
    @RequestMapping(value = "/getMessageWith", method = RequestMethod.GET)
    @PreAuthorize("hasRole('USER') || hasRole('TEACHER') || hasRole('ADMIN')")
    public ResponseEntity<?> getMessageWith(Authentication authentication, @RequestParam int to){
        try {
            if (authentication != null && authentication.getPrincipal() instanceof User) {
                User user = (User) authentication.getPrincipal();
                int userId = user.getId().intValue();
                var data = messageService.getMessageWith(userId, to);
                return ResponseEntity.ok(data);
            }
            else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
            }
        } catch (InternalServerException e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @Operation(summary = "Api 133: get contacts of user")
    @RequestMapping(value = "/getContact", method = RequestMethod.GET)
    @PreAuthorize("hasRole('USER') || hasRole('TEACHER') || hasRole('ADMIN')")
    public ResponseEntity<?> getContact(Authentication authentication){
        try {
            if (authentication != null && authentication.getPrincipal() instanceof User) {
                User user = (User) authentication.getPrincipal();
                int userId = user.getId().intValue();
                var data = messageService.getContact(userId);
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
