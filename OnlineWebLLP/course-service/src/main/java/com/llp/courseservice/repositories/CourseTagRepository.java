package com.llp.courseservice.repositories;

import com.llp.courseservice.entities.CourseTag;
import com.llp.courseservice.entities.keys.CourseTagKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface CourseTagRepository extends JpaRepository<CourseTag, CourseTagKey> {
    // for bestseller
    @Transactional
    @Modifying
    @Query(value = "INSERT INTO courseTag (courseId,tagId,createdAt) VALUES (:courseId,1,:createdAt)", nativeQuery = true)
    void updateBestsellerTag(@Param("courseId") String courseId, @Param("createdAt") LocalDate createdAt);

    @Transactional
    @Modifying
    @Query(value = "DELETE FROM courseTag WHERE courseTag.tagId = 1", nativeQuery = true)
    void deleteBestsellerTag();

    //for highest rated
    @Transactional
    @Modifying
    @Query(value = "INSERT INTO courseTag (courseId,tagId,createdAt) VALUES (:courseId,4,:createdAt)", nativeQuery = true)
    void updateHighestRatedTag(@Param("courseId") String courseId, @Param("createdAt") LocalDate createdAt);

    @Transactional
    @Modifying
    @Query(value = "DELETE FROM courseTag WHERE courseTag.tagId = 4", nativeQuery = true)
    void deleteHighestRatedTag();

    //for new
    @Transactional
    @Modifying
    @Query(value = "INSERT INTO courseTag (courseId,tagId,createdAt) VALUES (:courseId,2,:createdAt)", nativeQuery = true)
    void createNewTag(@Param("courseId") String courseId, @Param("createdAt") LocalDate createdAt);

    @Query(value = "SELECT ct FROM CourseTag ct WHERE ct.tag.id = 2 AND ct.createdAt =:createdAt")
    List<CourseTag> findByCreatedAtBefore(LocalDate createdAt);
}
