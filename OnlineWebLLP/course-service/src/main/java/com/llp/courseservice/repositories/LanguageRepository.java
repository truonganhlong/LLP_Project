package com.llp.courseservice.repositories;

import com.llp.courseservice.entities.Language;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LanguageRepository extends JpaRepository<Language, Long> {
    Language getById(int id);
}
