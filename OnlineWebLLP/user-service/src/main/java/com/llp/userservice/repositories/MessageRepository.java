package com.llp.userservice.repositories;

import com.llp.userservice.entities.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {
    @Query(value = "SELECT m FROM Message m WHERE m.userSend.id = :userId AND m.userReceive.id = :to OR m.userSend.id = :to AND m.userReceive.id = :userId ORDER BY m.createdAt DESC")
    List<Message> getMessageWith(@Param("userId") int userId, @Param("to") int to);
    @Query(value = "SELECT m FROM Message m WHERE m.userReceive.id = :userId OR m.userSend.id = :userId ORDER BY m.createdAt DESC")
    List<Message> getContact(@Param("userId") int userId);
}
