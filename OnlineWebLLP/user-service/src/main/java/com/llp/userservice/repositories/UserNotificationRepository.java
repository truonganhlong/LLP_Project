package com.llp.userservice.repositories;

import com.llp.userservice.entities.UserNotification;
import com.llp.userservice.entities.keys.UserNotificationKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface UserNotificationRepository extends JpaRepository<UserNotification, UserNotificationKey> {
    @Transactional
    @Modifying
    @Query(value = "INSERT INTO userNotification(userId, notificationId) VALUES (:userId, :notificationId)", nativeQuery = true)
    void create(@Param("userId") int userId, @Param("notificationId") int notificationId);

    @Query(value = "SELECT un.id.notificationId FROM UserNotification un WHERE un.id.userId = :userId")
    List<Integer> getNotificationIds(@Param("userId") int userId);
}
