package com.llp.userservice.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.llp.userservice.entities.keys.UserNotificationKey;
import jakarta.persistence.*;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Table(name = "userNotification")
public class UserNotification {
    @EmbeddedId
    private UserNotificationKey id;
    @ManyToOne
    @MapsId("userId")
    @JoinColumn(name = "userId")
    @JsonIgnore
    private User user;
    @ManyToOne
    @MapsId("notificationId")
    @JoinColumn(name = "notificationId")
    @JsonIgnore
    private Notification notification;
}
