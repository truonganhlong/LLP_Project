package com.llp.userservice.repositories;

import com.llp.userservice.entities.YourCourse;
import com.llp.userservice.entities.keys.YourCourseKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface YourCourseRepository extends JpaRepository<YourCourse, YourCourseKey> {
    @Query(value = "SELECT COUNT(*) FROM dbo.yourCourse WHERE dbo.yourCourse.courseId = :courseId", nativeQuery = true)
    long countByCourseId(@Param("courseId") String courseId);

    @Transactional
    @Modifying
    @Query(value = "INSERT INTO yourCourse (courseId,userId,orderId) VALUES(:courseId,:userId,:orderId)", nativeQuery = true)
    void create(@Param("courseId") String courseId, @Param("userId") int userId, @Param("orderId") String orderId);

    @Query(value = "SELECT yc FROM YourCourse yc WHERE yc.id.userId = :userId AND yc.id.courseId = :courseId")
    YourCourse getByUserAndCourse(@Param("userId") int userId, @Param("courseId") String courseId);

    @Query(value = "SELECT yc FROM YourCourse yc WHERE yc.id.userId = :userId")
    List<YourCourse> getByUser(@Param("userId") int userId);
}
