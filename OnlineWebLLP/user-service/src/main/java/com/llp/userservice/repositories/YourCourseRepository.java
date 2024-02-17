package com.llp.userservice.repositories;

import com.llp.userservice.entities.YourCourse;
import com.llp.userservice.entities.keys.YourCourseKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface YourCourseRepository extends JpaRepository<YourCourse, YourCourseKey> {
    @Query(value = "SELECT COUNT(*) FROM dbo.yourCourse WHERE dbo.yourCourse.courseId = :courseId", nativeQuery = true)
    long countByCourseId(@Param("courseId") String courseId);
}
