package com.llp.courseservice.repositories;

import com.llp.courseservice.entities.VisitCourse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface VisitCourseRepository extends JpaRepository<VisitCourse, Long> {
    @Query(value = "SELECT COUNT(*) FROM visitcourse WHERE visitcourse.userId = 0 AND visitcourse.courseId = :courseId AND DATE(visitcourse.visitAt) = :day", nativeQuery = true)
    int returnAnonymousVisit(@Param("courseId") String courseId, @Param("day") String day);

    @Query(value = "SELECT COUNT(*) FROM visitcourse WHERE visitcourse.userId != 0 AND visitcourse.courseId = :courseId AND DATE(visitcourse.visitAt) = :day", nativeQuery = true)
    int returnUserVisit(@Param("courseId") String courseId, @Param("day") String day);
}
