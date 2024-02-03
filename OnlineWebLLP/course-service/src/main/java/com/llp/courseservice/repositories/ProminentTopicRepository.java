package com.llp.courseservice.repositories;

import com.llp.courseservice.entities.ProminentTopic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProminentTopicRepository extends JpaRepository<ProminentTopic, Long> {
    ProminentTopic getById(int id);
}
   