package com.llp.courseservice.repositories;

import com.llp.courseservice.entities.LastViewCourse;
import com.llp.courseservice.entities.keys.LastViewCourseKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface LastViewCourseRepository extends JpaRepository<LastViewCourse, LastViewCourseKey> {
    @Transactional
    @Modifying
    @Query(value = "INSERT INTO dbo.lastViewCourse (courseId,userId) VALUES(:courseId,:userId)", nativeQuery = true)
    void create(@Param("courseId") String courseId, @Param("userId") int userId);

    @Transactional
    @Modifying
    @Query(value = "UPDATE dbo.lastViewCourse SET dbo.lastViewCourse.courseId = :courseId WHERE dbo.lastViewCourse.userId = :userId", nativeQuery = true)
    void update(@Param("courseId") String courseId, @Param("userId") int userId);

    @Query(value = "SELECT dbo.lastViewCourse.courseId FROM dbo.lastViewCourse WHERE dbo.lastViewCourse.userId = :userId", nativeQuery = true)
    String getLastViewCourse(@Param("userId") int userId);
}
