package com.llp.userservice.services.impl;

import com.llp.sharedproject.exceptions.InternalServerException;
import com.llp.sharedproject.exceptions.NotFoundException;
import com.llp.sharedproject.sharedFunc.SharedFunction;
import com.llp.userservice.dtos.message.ChatResponse;
import com.llp.userservice.dtos.message.MessageResponse;
import com.llp.userservice.entities.Message;
import com.llp.userservice.entities.User;
import com.llp.userservice.mappers.MessageMapper;
import com.llp.userservice.repositories.MessageRepository;
import com.llp.userservice.repositories.UserRepository;
import com.llp.userservice.services.MessageService;
import com.llp.userservice.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MessageServiceImpl implements MessageService {
    private final MessageRepository messageRepository;
    private final UserRepository userRepository;
    private final UserService userService;

    @Override
    public void send(int userId, String to, String content) {
        try {
            User userTo = userRepository.findByEmail(to)
                    .orElseThrow(() -> new NotFoundException("User not found"));
            Message message = Message.builder()
                    .message(SharedFunction.encodeMessage(content))
                    .createdAt(LocalDateTime.now())
                    .userReceive(userTo)
                    .userSend(userRepository.getById(userId).get())
                    .build();
            messageRepository.save(message);
        } catch (NotFoundException e){
            throw new NotFoundException(e.getMessage());
        } catch (Exception e){
            throw new InternalServerException("Server Error");
        }
    }

    @Override
    public ChatResponse getMessageWith(int userId, int to) {
        try {
            List<Message> messages = messageRepository.getMessageWith(userId,to);
            List<MessageResponse> messageResponses = messages.stream().map(MessageMapper :: convertToResponse).collect(Collectors.toList());
            ChatResponse data = ChatResponse.builder()
                    .message(messageResponses)
                    .userReceive(userService.getOtherUserInformation(to))
                    .userSend(userService.getOtherUserInformation(userId))
                    .build();
            return data;
        } catch (Exception e){
            throw new InternalServerException("Server Error");
        }
    }

    @Override
    public List<Integer> getContact(int userId) {
        try {
            List<Integer> data = new ArrayList<>();
            List<Message> messages = messageRepository.getContact(userId);
            for (var message:messages) {
                if(message.getUserSend().getId() == userId){
                    if(!data.contains(message.getUserReceive().getId().intValue())){
                        data.add(message.getUserReceive().getId().intValue());
                    }
                } else {
                    if(!data.contains(message.getUserSend().getId().intValue())){
                        data.add(message.getUserSend().getId().intValue());
                    }
                }
            }
            return data;
        } catch (Exception e){
            throw new InternalServerException("Server Error");
        }
    }
}
