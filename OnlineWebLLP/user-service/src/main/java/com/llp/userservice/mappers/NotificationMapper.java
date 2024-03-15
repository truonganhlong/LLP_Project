package com.llp.userservice.mappers;

import com.llp.userservice.dtos.notification.NotificationResponse;
import com.llp.userservice.entities.Notification;

import java.time.format.DateTimeFormatter;

public class NotificationMapper {
    public static NotificationResponse convertToResponse(Notification notification){
        return NotificationResponse.builder()
                .id(notification.getId())
                .message(notification.getMessage())
                .createdAt(notification.getCreatedAt().format(DateTimeFormatter.ofPattern("HH:mm")))
                .build();
    }
}
