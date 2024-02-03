package com.llp.courseservice.repositories;

import com.llp.courseservice.entities.SubCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubCategoryRepository extends JpaRepository<SubCategory, Long> {
    interface SubCategoryByName {
        Long getId();
        String getName();
    }
    SubCategory getById(int id);
    @Query(value = "SELECT dbo.subCategory.*\n" +
            "FROM     dbo.subCategory\n" +
            "WHERE dbo.subCategory.categoryId = :categoryId", nativeQuery = true)
    List<SubCategory> getByAdminFilterByCategory(@Param("categoryId") int categoryId);
    @Query(value = "SELECT dbo.subCategory.id, dbo.subCategory.name\n" +
            "FROM     dbo.subCategory\n" +
            "WHERE dbo.subCategory.categoryId = :categoryId AND dbo.subCategory.status = 1", nativeQuery = true)
    List<SubCategoryByName> getByUserFilterByCategory(@Param("categoryId") int categoryId);
}
