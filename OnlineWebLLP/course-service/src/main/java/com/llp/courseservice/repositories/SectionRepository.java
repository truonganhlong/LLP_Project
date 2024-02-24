package com.llp.courseservice.repositories;

import com.llp.courseservice.entities.Section;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface SectionRepository extends JpaRepository<Section, Long> {
    Section getById(int id);
    @Query(value = "SELECT * FROM dbo.section WHERE dbo.section.courseId = :courseId", nativeQuery = true)
    List<Section> getAllByCourse(@Param("courseId") String courseId);
}
