package com.llp.courseservice.repositories;

import com.llp.courseservice.entities.Level;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LevelRepository extends JpaRepository<Level, Long> {
    Level getById(int id);
}
