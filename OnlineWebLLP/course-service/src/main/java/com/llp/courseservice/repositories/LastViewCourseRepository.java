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
    @Query(value = "INSERT INTO lastViewCourse (courseId,userId) VALUES(:courseId,:userId)", nativeQuery = true)
    void create(@Param("courseId") String courseId, @Param("userId") int userId);
}
