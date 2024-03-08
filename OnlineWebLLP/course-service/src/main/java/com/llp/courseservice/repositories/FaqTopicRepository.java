package com.llp.courseservice.repositories;

import com.llp.courseservice.entities.FaqTopic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FaqTopicRepository extends JpaRepository<FaqTopic, Long> {
    @Query(value = "SELECT f FROM FaqTopic f WHERE f.topic.id = :topicId")
    List<FaqTopic> getByTopic(@Param("topicId") int topicId);
    FaqTopic getById(int id);
}
