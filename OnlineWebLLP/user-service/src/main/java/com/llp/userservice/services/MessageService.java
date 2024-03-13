package com.llp.userservice.services;

import com.llp.userservice.dtos.message.ChatResponse;
import com.llp.userservice.dtos.message.MessageResponse;

import java.util.List;

public interface MessageService {
    void send(int userId, String to, String content);
    ChatResponse getMessageWith(int userId, int to);
    List<Integer> getContact(int userId);
}
