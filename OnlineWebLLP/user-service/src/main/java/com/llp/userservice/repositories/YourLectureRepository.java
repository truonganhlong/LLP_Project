package com.llp.userservice.repositories;

import com.llp.userservice.entities.YourLecture;
import com.llp.userservice.entities.keys.YourLectureKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface YourLectureRepository extends JpaRepository<YourLecture, YourLectureKey> {
    @Query(value = "SELECT COUNT(*) FROM yourLecture WHERE yourLecture.userId = :userId AND yourLecture.courseId = :courseId", nativeQuery = true)
    long countByUserAndCourse(@Param("userId") int userId, @Param("courseId") String courseId);

    @Transactional
    @Modifying
    @Query(value = "INSERT INTO yourLecture (userId,courseId,lectureId) VALUES(:userId,:courseId,:lectureId)", nativeQuery = true)
    void create(@Param("userId") int userId, @Param("courseId") String courseId, @Param("lectureId") int lectureId);

    @Transactional
    @Modifying
    @Query(value = "UPDATE yourLecture SET yourLecture.lectureId = :lectureId WHERE yourLecture.userId = :userId AND yourLecture.courseId = :courseId", nativeQuery = true)
    void update(@Param("userId") int userId, @Param("courseId") String courseId, @Param("lectureId") int lectureId);

    @Query(value = "SELECT yl FROM YourLecture yl WHERE yl.id.userId = :userId")
    List<YourLecture> getByUser(@Param("userId") int userId);
}
