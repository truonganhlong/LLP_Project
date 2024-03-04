package com.llp.courseservice.repositories;

import com.llp.courseservice.entities.CourseTag;
import com.llp.courseservice.entities.keys.CourseTagKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface CourseTagRepository extends JpaRepository<CourseTag, CourseTagKey> {
    
}
