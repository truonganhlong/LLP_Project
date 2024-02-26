package com.llp.courseservice.repositories;

import com.llp.courseservice.entities.Lecture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Repository
public interface LectureRepository extends JpaRepository<Lecture, Long> {
    Lecture getById(int id);

    @Query(value = "SELECT * FROM dbo.lecture WHERE dbo.lecture.sectionId = :sectionId", nativeQuery = true)
    List<Lecture> getAllBySection(@Param("sectionId") int sectionId);

    @Transactional
    @Modifying
    @Query(value = "UPDATE dbo.course SET dbo.course.duration = dbo.course.duration + :duration WHERE dbo.course.id = :courseId", nativeQuery = true)
    void updateCourseDuration(@Param("duration") int duration, @Param("courseId") String courseId);

    @Query(value = "SELECT l FROM Lecture l\n" +
            "JOIN l.section s\n" +
            "JOIN s.course c\n" +
            "WHERE c.id = :courseId AND l.isFree = true")
    List<Lecture> getAllPreviewLecture(@Param("courseId") UUID courseId);
}
