package com.llp.commonservice.repositories;

import com.llp.commonservice.entities.Intro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IntroRepository extends JpaRepository<Intro, Long> {
    Intro getById(int id);
    List<Intro> getAllByIntroMapId(int introMapId);
    @Query(value = "SELECT *\n" +
            "FROM     intro\n" +
            "WHERE intro.introMapId = :introMapId AND intro.status = 1", nativeQuery = true)
    List<Intro> getAllByIntroMapIdByUser(@Param("introMapId") int introMapId);
}
