package com.llp.userservice.controllers;

import com.llp.sharedproject.exceptions.InternalServerException;
import com.llp.userservice.services.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
//@CrossOrigin(origins = "http://localhost:3000")
public class WebSocketController {
    private final MessageService messageService;
    @MessageMapping("/messages")
    @SendTo("/topic/chat/{userId}/{to}")
    public void sendMessage(@DestinationVariable int userId, @DestinationVariable String to, String content){
        try {
            messageService.send(userId,to,content);
        } catch (Exception e){
            throw new InternalServerException("Server error");
        }
    }
}
