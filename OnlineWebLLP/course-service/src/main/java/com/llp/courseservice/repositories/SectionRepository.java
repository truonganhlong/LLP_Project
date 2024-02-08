package com.llp.courseservice.repositories;

import com.llp.courseservice.entities.Section;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SectionRepository extends JpaRepository<Section, Long> {
    Section getById(int id);
}
