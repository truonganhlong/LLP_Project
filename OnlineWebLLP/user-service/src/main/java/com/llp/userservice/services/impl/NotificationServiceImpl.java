package com.llp.userservice.services.impl;

import com.llp.sharedproject.exceptions.BadRequestException;
import com.llp.sharedproject.exceptions.InternalServerException;
import com.llp.userservice.dtos.notification.NotificationResponse;
import com.llp.userservice.entities.Notification;
import com.llp.userservice.mappers.NotificationMapper;
import com.llp.userservice.repositories.NotificationRepository;
import com.llp.userservice.repositories.UserNotificationRepository;
import com.llp.userservice.repositories.UserRoleRepository;
import com.llp.userservice.repositories.YourCourseRepository;
import com.llp.userservice.services.NotificationService;
import com.llp.userservice.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class NotificationServiceImpl implements NotificationService {
    private final NotificationRepository notificationRepository;
    private final UserRoleRepository userRoleRepository;
    private final YourCourseRepository yourCourseRepository;
    private final UserNotificationRepository userNotificationRepository;
    private final UserService userService;
    @Override
    public void adminSendToUserAndTeacher(int userId, String forWho, String message) {
        try {
            if(forWho.equals("student")){
                List<Integer> userIds = userRoleRepository.returnAllUserIds();
                for (var user:userIds) {
                    Notification notification = Notification.builder()
                            .message(message)
                            .createdAt(LocalDateTime.now())
                            .createdBy((long) userId)
                            .build();
                    notificationRepository.save(notification);
                    userNotificationRepository.create(user,notification.getId().intValue());
                }
            } else if(forWho.equals("teacher")){
                List<Integer> userIds = userRoleRepository.returnAllTeacherIds();
                for (var user:userIds) {
                    Notification notification = Notification.builder()
                            .message(message)
                            .createdAt(LocalDateTime.now())
                            .createdBy((long) userId)
                            .build();
                    notificationRepository.save(notification);
                    userNotificationRepository.create(user,notification.getId().intValue());
                }
            } else {
                throw new BadRequestException("Wrong type of forWho");
            }
        } catch (BadRequestException e){
            throw new BadRequestException(e.getMessage());
        } catch (Exception e){
            throw new InternalServerException("Server error");
        }
    }

    @Override
    public void teacherSendToUser(int userId, String courseId, String message) {
        try {
            List<Integer> userIds = yourCourseRepository.getStudentIds(courseId);
            for (var user:userIds) {
                Notification notification = Notification.builder()
                        .message(message)
                        .createdAt(LocalDateTime.now())
                        .createdBy((long) userId)
                        .build();
                notificationRepository.save(notification);
                userNotificationRepository.create(user,notification.getId().intValue());
            }
        } catch (Exception e){
            throw new InternalServerException("Server error");
        }
    }

    @Override
    public List<NotificationResponse> getYourNotification(int userId) {
        try {
            List<Integer> notificationIds = userNotificationRepository.getNotificationIds(userId);
            List<NotificationResponse> data = new ArrayList<>();
            for (var notificationId:notificationIds) {
                Notification notification = notificationRepository.getById(notificationId);
                NotificationResponse notificationResponse = NotificationMapper.convertToResponse(notification);
                notificationResponse.setUserSend(userService.getOtherUserInformation(notification.getCreatedBy().intValue()));
                data.add(notificationResponse);
            }
            return data;
        } catch (Exception e){
            throw new InternalServerException("Server error");
        }
    }
}
