package com.llp.courseservice.repositories;

import com.llp.courseservice.entities.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface TagRepository extends JpaRepository<Tag, Long> {
    Tag getById(int id);

    @Query(value = "SELECT tag.name\n" +
            "FROM     course INNER JOIN\n" +
            "         courseTag ON course.id = courseTag.courseId INNER JOIN\n" +
            "         tag ON courseTag.tagId = tag.id\n" +
            "WHERE  course.id = :courseId", nativeQuery = true)
    List<String> getTagNameByCourseId(@Param("courseId") String courseId);
}
