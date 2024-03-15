package com.llp.userservice.dtos.notification;

import com.llp.userservice.dtos.user.UserResponse;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class NotificationResponse {
    private Long id;
    private String message;
    private String createdAt;
    private UserResponse userSend;
}
