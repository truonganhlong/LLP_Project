package com.llp.courseservice.repositories;


import com.llp.courseservice.dtos.Course.CourseCardJpql;
import com.llp.courseservice.dtos.Course.CourseDetailJpql;
import com.llp.courseservice.entities.Course;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Repository
public interface CourseRepository extends PagingAndSortingRepository<Course, UUID>, JpaRepository<Course, UUID> {
    interface CourseOverview{
        UUID getId();
        String getName();
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
    @Query(value = "SELECT c FROM Course c WHERE c.id = :id")
    Course getById(@Param("id") UUID id);
    @Query(value = "SELECT dbo.course.id, dbo.course.name, dbo.course.updatedAt, dbo.course.duration, dbo.[level].name AS level, dbo.course.overview, dbo.course.target\n" +
            "FROM     dbo.course INNER JOIN\n" +
            "         dbo.[level] ON dbo.course.levelId = dbo.[level].id\n" +
            "WHERE    dbo.course.id = :id", nativeQuery = true)
    CourseOverview getOverviewById(@Param("id") String id);

    @Query(value = "SELECT dbo.course.id, dbo.course.imageLink, dbo.course.name, dbo.course.createdBy, dbo.course.rating, dbo.course.ratingNum, dbo.course.price\n" +
            "FROM     dbo.course\n" +
            "WHERE  dbo.course.isProminent = 1", nativeQuery = true)
    List<CourseCard> getAllProminentCourse(Pageable pageable);

    @Query(value = "SELECT dbo.course.id, dbo.course.imageLink, dbo.course.name, dbo.course.createdBy, dbo.course.rating, dbo.course.ratingNum, dbo.course.price\n" +
            "FROM     dbo.course INNER JOIN\n" +
            "         dbo.courseTopic ON dbo.course.id = dbo.courseTopic.courseId INNER JOIN\n" +
            "         dbo.topic ON dbo.courseTopic.topicId = dbo.topic.id\n" +
            "WHERE  dbo.topic.id = :topicId AND dbo.course.isProminent = 1\n" +
            "ORDER BY dbo.course.ratingNum DESC", nativeQuery = true)
    List<CourseCard> getAllProminentCourseByTopicId(@Param("topicId") int topicId, Pageable pageable);

    @Query(value = "SELECT dbo.course.id, dbo.course.imageLink, dbo.course.name, dbo.course.createdBy, dbo.course.rating, dbo.course.ratingNum, dbo.course.price\n" +
            "FROM     dbo.course INNER JOIN\n" +
            "         dbo.courseTopic ON dbo.course.id = dbo.courseTopic.courseId INNER JOIN\n" +
            "         dbo.topic ON dbo.courseTopic.topicId = dbo.topic.id INNER JOIN\n" +
            "         dbo.subCategory ON dbo.topic.subCategoryId = dbo.subCategory.id\n" +
            "WHERE  dbo.subCategory.id = :subCategoryId AND dbo.course.isProminent = 1\n" +
            "ORDER BY dbo.course.ratingNum DESC", nativeQuery = true)
    List<CourseCard> getAllProminentCourseBySubCategoryId(@Param("subCategoryId") int subCategoryId, Pageable pageable);

    @Query(value = "SELECT dbo.course.id, dbo.course.imageLink, dbo.course.name, dbo.course.createdBy, dbo.course.rating, dbo.course.ratingNum, dbo.course.price\n" +
            "FROM     dbo.course INNER JOIN\n" +
            "         dbo.courseTopic ON dbo.course.id = dbo.courseTopic.courseId INNER JOIN\n" +
            "         dbo.topic ON dbo.courseTopic.topicId = dbo.topic.id INNER JOIN\n" +
            "         dbo.subCategory ON dbo.topic.subCategoryId = dbo.subCategory.id INNER JOIN\n" +
            "         dbo.category ON dbo.subCategory.categoryId = dbo.category.id\n" +
            "WHERE  dbo.category.id = :categoryId AND dbo.course.isProminent = 1", nativeQuery = true)
    List<CourseCard> getAllProminentCourseByCategoryId(@Param("categoryId") int categoryId, Pageable pageable);

    @Query("SELECT new com.llp.courseservice.dtos.Course.CourseCardJpql(c.id, c.imageLink, c.name, c.createdBy, c.rating, c.ratingNum, c.price, c.createdAt, c.duration, t.id, sc.id, le.id, la.id) " +
            "FROM Course c\n" +
            "JOIN c.level le\n" +
            "JOIN c.language la\n" +
            "JOIN c.courseTopics ct\n" +
            "JOIN ct.topic t\n" +
            "JOIN t.subCategory sc\n" +
            "WHERE t.id = :topicId AND c.status = true")
    List<CourseCardJpql> getAllCourseByTopicId(@Param("topicId") int topicId, Pageable pageable);

    @Query("SELECT new com.llp.courseservice.dtos.Course.CourseCardJpql(c.id, c.imageLink, c.name, c.createdBy, c.rating, c.ratingNum, c.price, c.createdAt, c.duration, t.id, sc.id, le.id, la.id) " +
            "FROM Course c\n" +
            "JOIN c.level le\n" +
            "JOIN c.language la\n" +
            "JOIN c.courseTopics ct\n" +
            "JOIN ct.topic t\n" +
            "JOIN t.subCategory sc\n" +
            "WHERE sc.id = :subCategoryId AND c.status = true")
    List<CourseCardJpql> getAllCourseBySubCategoryId(@Param("subCategoryId") int subCategoryId, Pageable pageable);

    @Query("SELECT new com.llp.courseservice.dtos.Course.CourseCardJpql(c.id, c.imageLink, c.name, c.createdBy, c.rating, c.ratingNum, c.price, c.createdAt, c.duration, t.id, sc.id, le.id, la.id) " +
            "FROM Course c\n" +
            "JOIN c.level le\n" +
            "JOIN c.language la\n" +
            "JOIN c.courseTopics ct\n" +
            "JOIN ct.topic t\n" +
            "JOIN t.subCategory sc\n" +
            "JOIN sc.category ca\n" +
            "WHERE ca.id = :categoryId AND c.status = true")
    List<CourseCardJpql> getAllCourseByCategoryId(@Param("categoryId") int categoryId, Pageable pageable);

    @Query("SELECT new com.llp.courseservice.dtos.Course.CourseCardJpql(c.id, c.imageLink, c.name, c.createdBy, c.rating, c.ratingNum, c.price, c.createdAt, c.duration, t.id, sc.id, le.id, la.id) " +
            "FROM Course c\n" +
            "JOIN c.level le\n" +
            "JOIN c.language la\n" +
            "JOIN c.courseTopics ct\n" +
            "JOIN ct.topic t\n" +
            "JOIN t.subCategory sc\n" +
            "WHERE c.name LIKE CONCAT('%', :keyword, '%') AND c.status = true")
    List<CourseCardJpql> searchCourseByName(@Param("keyword") String keyword, Pageable pageable);

    List<Course> getByCreatedBy(int createdBy);

    @Query(value = "SELECT dbo.course.id, dbo.course.imageLink, dbo.course.name, dbo.course.createdBy, dbo.course.rating, dbo.course.ratingNum, dbo.course.price\n" +
            "FROM     dbo.course\n" +
            "WHERE  dbo.course.id = :id", nativeQuery = true)
    CourseCard getCourseCardById(@Param("id") String id);

    @Query("SELECT new com.llp.courseservice.dtos.Course.CourseDetailJpql(c.id,c.name,c.description,c.overview,c.forWho,c.requirement,c.target,c.imageLink,c.promoVideoLink,c.rating,c.ratingNum,c.saleNum,c.price,c.duration,c.createdAt,c.updatedAt,c.createdBy,t.id,sc.id,ca.id,le.id,la.id)\n" +
            "FROM Course c\n" +
            "JOIN c.level le\n" +
            "JOIN c.language la\n" +
            "JOIN c.courseTopics ct\n" +
            "JOIN ct.topic t\n" +
            "JOIN t.subCategory sc\n" +
            "JOIN sc.category ca\n" +
            "WHERE c.id = :courseId\n" +
            "ORDER BY t.id LIMIT 1")
    CourseDetailJpql getCourseDetail(@Param("courseId") UUID courseId);

}
