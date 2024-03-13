package com.llp.userservice.controllers;

import com.llp.sharedproject.exceptions.InternalServerException;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class WebSocketController {
    private final SimpMessagingTemplate simpMessagingTemplate;
    @MessageMapping("/ws")
    @SendTo("/topic/message")
    public void sendMessage(@RequestParam String to, @RequestParam String content){
        try {
            simpMessagingTemplate.convertAndSend("/topic/message/" + to, content);
        } catch (Exception e){
            throw new InternalServerException("Server error");
        }
    }
}
