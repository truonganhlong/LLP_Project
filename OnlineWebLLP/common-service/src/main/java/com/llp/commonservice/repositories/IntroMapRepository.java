package com.llp.commonservice.repositories;

import com.llp.commonservice.entities.IntroMap;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IntroMapRepository extends JpaRepository<IntroMap, Long> {
    IntroMap getById(int id);
}
