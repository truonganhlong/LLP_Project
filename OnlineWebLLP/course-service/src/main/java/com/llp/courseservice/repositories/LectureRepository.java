package com.llp.courseservice.repositories;

import com.llp.courseservice.entities.Lecture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LectureRepository extends JpaRepository<Lecture, Long> {
    Lecture getById(int id);
}
