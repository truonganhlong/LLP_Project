package com.llp.courseservice.repositories;

import com.llp.courseservice.entities.Review;
import com.llp.courseservice.entities.keys.ReviewKey;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ReviewRepository extends PagingAndSortingRepository<Review, ReviewKey>,JpaRepository<Review, ReviewKey> {
    interface ReviewData{
        UUID getCourseId();
        Long getUserId();
        String getContent();
        int getRating();
        boolean setProminent();
        String getName();

    }

    @Query(value = "SELECT dbo.review.*, dbo.course.name\n" +
            "FROM     dbo.review INNER JOIN\n" +
            "dbo.course ON dbo.review.courseId = dbo.course.id WHERE dbo.review.isProminent = 1", nativeQuery = true)
    List<ReviewData> getAllProminentReview(Pageable pageable);

    @Query(value = "SELECT dbo.review.*, dbo.course.name\n" +
            "FROM     dbo.review INNER JOIN\n" +
            "dbo.course ON dbo.review.courseId = dbo.course.id WHERE dbo.course.id = :courseId", nativeQuery = true)
    List<ReviewData> getAllReviewByCourse(@Param("courseId") String courseId, Pageable pageable);
}
