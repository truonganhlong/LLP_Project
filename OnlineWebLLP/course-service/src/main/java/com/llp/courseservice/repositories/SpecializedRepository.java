package com.llp.courseservice.repositories;

import com.llp.courseservice.entities.Specialized;
import com.llp.courseservice.entities.keys.SpecializedKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface SpecializedRepository extends JpaRepository<Specialized, SpecializedKey> {
    @Query(value = "SELECT s.id.userId FROM Specialized s WHERE s.id.categoryId = :categoryId")
    List<Integer> getAllTeacherByCategory(@Param("categoryId") int categoryId);
    @Query(value = "SELECT s.id.userId FROM Specialized s WHERE s.id.subCategoryId = :subCategoryId")
    List<Integer> getAllTeacherBySubCategory(@Param("subCategoryId") int subCategoryId);
    @Transactional
    @Modifying
    @Query(value = "INSERT INTO specialized (userId,categoryId,subCategoryId) VALUES(:userId,:categoryId,:subCategoryId)", nativeQuery = true)
    void create(@Param("userId") int userId, @Param("categoryId") int categoryId, @Param("subCategoryId") int subCategoryId);
    @Query(value = "SELECT s FROM Specialized s WHERE s.id.userId = :userId AND s.id.categoryId = :categoryId AND s.id.subCategoryId = :subCategoryId")
    Specialized getOne(@Param("userId") int userId, @Param("categoryId") int categoryId, @Param("subCategoryId") int subCategoryId);
}
