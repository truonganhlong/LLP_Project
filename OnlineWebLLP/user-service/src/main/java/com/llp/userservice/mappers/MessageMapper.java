package com.llp.userservice.mappers;

import com.llp.sharedproject.sharedFunc.SharedFunction;
import com.llp.userservice.dtos.message.MessageResponse;
import com.llp.userservice.entities.Message;

import java.time.format.DateTimeFormatter;

public class MessageMapper {
    public static MessageResponse convertToResponse (Message message){
        return MessageResponse.builder()
                .id(message.getId())
                .message(SharedFunction.decodeMessage(message.getMessage()))
                .createdAt(message.getCreatedAt().format(DateTimeFormatter.ofPattern("HH:mm dd/MM/yyyy")))
                .userReceiveId(message.getUserReceive().getId().intValue())
                .userSendId(message.getUserSend().getId().intValue())
                .build();
    }
}
