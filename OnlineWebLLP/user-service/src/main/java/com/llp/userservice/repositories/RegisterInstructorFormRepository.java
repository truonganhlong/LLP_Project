package com.llp.userservice.repositories;

import com.llp.userservice.entities.RegisterInstructorForm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface RegisterInstructorFormRepository extends JpaRepository<RegisterInstructorForm, Long> {
    @Transactional
    @Modifying
    @Query(value = "INSERT INTO registerInstructorForm (userId,questionId,answerId) VALUES(:userId,:questionId,:answerId)", nativeQuery = true)
    void create(@Param("userId") int userId, @Param("questionId") int questionId, @Param("answerId") int answerId);
}
