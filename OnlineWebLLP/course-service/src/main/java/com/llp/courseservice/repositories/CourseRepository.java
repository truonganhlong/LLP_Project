package com.llp.courseservice.repositories;


import com.llp.courseservice.entities.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Repository
public interface CourseRepository extends PagingAndSortingRepository<Course, UUID>, JpaRepository<Course, UUID> {
    interface CourseOverview{
        UUID getId();
        String getName();
        @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
        LocalDateTime getUpdatedAt();
        int getDuration();
        String getLevel();
        String getOverview();
        String getTarget();
    }
    interface CourseCard{
        UUID getId();
        String getImageLink();
        String getName();
        int getCreatedBy();
        double getRating();
        int getRatingNum();
        double getPrice();
    }
    Course getById(UUID id);
    @Query(value = "SELECT dbo.course.id, dbo.course.name, dbo.course.updatedAt, dbo.course.duration, dbo.[level].name AS level, dbo.course.overview, dbo.course.target\n" +
            "FROM     dbo.course INNER JOIN\n" +
            "         dbo.[level] ON dbo.course.levelId = dbo.[level].id\n" +
            "WHERE    dbo.course.id = :id", nativeQuery = true)
    CourseOverview getOverviewById(@Param("id") UUID id);

    @Query(value = "SELECT dbo.course.id, dbo.course.imageLink, dbo.course.name, dbo.course.createdBy, dbo.course.rating, dbo.course.ratingNum, dbo.course.price\n" +
            "FROM     dbo.course\n" +
            "WHERE  dbo.course.isProminent = 1", nativeQuery = true)
    List<CourseCard> getAllProminentCourse();

    @Query(value = "SELECT dbo.course.id, dbo.course.imageLink, dbo.course.name, dbo.course.createdBy, dbo.course.rating, dbo.course.ratingNum, dbo.course.price\n" +
            "FROM     dbo.course INNER JOIN\n" +
            "         dbo.courseTopic ON dbo.course.id = dbo.courseTopic.courseId INNER JOIN\n" +
            "         dbo.topic ON dbo.courseTopic.topicId = dbo.topic.id\n" +
            "WHERE  dbo.topic.id = :topicId AND dbo.course.isProminent = 1", nativeQuery = true)
    List<CourseCard> getAllProminentCourseByTopicId(@Param("topicId") int topicId);

    @Query(value = "SELECT dbo.course.id, dbo.course.imageLink, dbo.course.name, dbo.course.createdBy, dbo.course.rating, dbo.course.ratingNum, dbo.course.price\n" +
            "FROM     dbo.course INNER JOIN\n" +
            "         dbo.courseTopic ON dbo.course.id = dbo.courseTopic.courseId INNER JOIN\n" +
            "         dbo.topic ON dbo.courseTopic.topicId = dbo.topic.id INNER JOIN\n" +
            "         dbo.subCategory ON dbo.topic.subCategoryId = dbo.subCategory.id\n" +
            "WHERE  dbo.subCategory.id = :subCategoryId AND dbo.course.isProminent = 1", nativeQuery = true)
    List<CourseCard> getAllProminentCourseBySubCategoryId(@Param("subCategoryId") int subCategoryId);

    @Query(value = "SELECT dbo.course.id, dbo.course.imageLink, dbo.course.name, dbo.course.createdBy, dbo.course.rating, dbo.course.ratingNum, dbo.course.price\n" +
            "FROM     dbo.course INNER JOIN\n" +
            "         dbo.courseTopic ON dbo.course.id = dbo.courseTopic.courseId INNER JOIN\n" +
            "         dbo.topic ON dbo.courseTopic.topicId = dbo.topic.id INNER JOIN\n" +
            "         dbo.subCategory ON dbo.topic.subCategoryId = dbo.subCategory.id INNER JOIN\n" +
            "         dbo.category ON dbo.subCategory.categoryId = dbo.category.id\n" +
            "WHERE  dbo.category.id = :categoryId AND dbo.course.isProminent = 1", nativeQuery = true)
    List<CourseCard> getAllProminentCourseByCategoryId(@Param("categoryId") int categoryId);

}
