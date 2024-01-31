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

    @Query(value = "SELECT dbo.tag.name\n" +
            "FROM     dbo.course INNER JOIN\n" +
            "         dbo.courseTag ON dbo.course.id = dbo.courseTag.courseId INNER JOIN\n" +
            "         dbo.tag ON dbo.courseTag.tagId = dbo.tag.id\n" +
            "WHERE  dbo.course.id = :courseId", nativeQuery = true)
    List<String> getTagNameByCourseId(@Param("courseId") UUID courseId);
}
