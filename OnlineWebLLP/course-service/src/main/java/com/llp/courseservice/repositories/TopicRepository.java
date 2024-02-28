package com.llp.courseservice.repositories;

import com.llp.courseservice.entities.Topic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TopicRepository extends JpaRepository<Topic, Long> {
    interface TopicByName {
        Long getId();
        String getName();
    }
    Topic getById(int id);
    @Query(value = "SELECT topic.*\n" +
            "FROM     topic\n" +
            "WHERE topic.subCategoryId = :subCategoryId", nativeQuery = true)
    List<Topic> getByAdminFilterBySubCategory(@Param("subCategoryId") int subCategoryId);
    @Query(value = "SELECT topic.id, topic.name\n" +
            "FROM     topic\n" +
            "WHERE topic.subCategoryId = :subCategoryId AND topic.status = 1", nativeQuery = true)
    List<TopicByName> getByUserFilterBySubCategory(@Param("subCategoryId") int subCategoryId);

}
