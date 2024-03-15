package com.llp.userservice.services;

import com.llp.userservice.dtos.notification.NotificationResponse;

import java.util.List;

public interface NotificationService {
    void adminSendToUserAndTeacher(int userId, String forWho, String message);
    void teacherSendToUser(int userId, String courseId, String message);
    List<NotificationResponse> getYourNotification(int userId);
}
