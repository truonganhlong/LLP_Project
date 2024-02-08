package com.llp.courseservice.repositories;

import com.llp.courseservice.entities.CourseTopic;
import com.llp.courseservice.entities.keys.CourseTopicKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface CourseTopicRepository extends JpaRepository<CourseTopic, CourseTopicKey> {
    @Transactional
    @Modifying
    @Query(value = "INSERT INTO courseTopic (courseId,topicId) VALUES(:courseId,:topicId)", nativeQuery = true)
    void create(@Param("courseId") String courseId, @Param("topicId") int topicId);
}
