package com.llp.courseservice.repositories;

import com.llp.courseservice.entities.Review;
import com.llp.courseservice.entities.keys.ReviewKey;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
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
        LocalDateTime getCreatedAt();

    }

    @Query(value = "SELECT dbo.review.*, dbo.course.name\n" +
            "FROM     dbo.review INNER JOIN\n" +
            "dbo.course ON dbo.review.courseId = dbo.course.id WHERE dbo.review.isProminent = 1", nativeQuery = true)
    List<ReviewData> getAllProminentReview(Pageable pageable);

    @Query(value = "SELECT dbo.review.*, dbo.course.name\n" +
            "FROM     dbo.review INNER JOIN\n" +
            "dbo.course ON dbo.review.courseId = dbo.course.id WHERE dbo.course.id = :courseId", nativeQuery = true)
    List<ReviewData> getAllReviewByCourse(@Param("courseId") String courseId, Pageable pageable);

    @Query(value = "SELECT dbo.review.*, dbo.course.name\n" +
            "FROM     dbo.review INNER JOIN\n" +
            "dbo.course ON dbo.review.courseId = dbo.course.id", nativeQuery = true)
    List<ReviewData> getAllReview(Pageable pageable);
    @Transactional
    @Modifying
    @Query(value = "INSERT INTO review (courseId,userId,content,rating,createdAt,isProminent) VALUES(:courseId,:userId,:content,:rating,:createdAt,0)", nativeQuery = true)
    void create(@Param("courseId") String courseId, @Param("userId") int userId, @Param("content") String content, @Param("rating") int rating, @Param("createdAt") LocalDateTime createdAt);
    @Transactional
    @Modifying
    @Query(value = "UPDATE dbo.review SET dbo.review.isProminent = 1 WHERE dbo.review.courseId = :courseId AND dbo.review.userId = :userId", nativeQuery = true)
    void updateReviewToProminent(@Param("courseId") String courseId, @Param("userId") int userId);

    @Query(value = "SELECT COUNT(*) FROM dbo.review WHERE dbo.review.courseId = :courseId AND dbo.review.rating = :rating", nativeQuery = true)
    int reviewCountFilterByRating(@Param("courseId") String courseId, @Param("rating") int rating);
}
