package com.llp.userservice.mappers;

import com.llp.userservice.dtos.message.MessageResponse;
import com.llp.userservice.entities.Message;

import java.time.format.DateTimeFormatter;

public class MessageMapper {
    public static MessageResponse convertToResponse (Message message){
        return MessageResponse.builder()
                .id(message.getId())
                .message(message.getMessage())
                .createdAt(message.getCreatedAt().format(DateTimeFormatter.ofPattern("HH:mm")))
                .userReceiveId(message.getUserReceive().getId().intValue())
                .userSendId(message.getUserSend().getId().intValue())
                .build();
    }
}
