package com.llp.courseservice.repositories;

import com.llp.courseservice.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    interface CategoryByName {
        Long getId();
        String getName();
    }
    interface CategoryByUser {
        Long getId();
        String getName();
        String getImageLink();
    }
    Category getById(int id);
    @Query(value = "SELECT id, name, imageLink\n" +
            "FROM     dbo.category\n" +
            "WHERE dbo.category.status = 1", nativeQuery = true)
    List<CategoryByUser> getAllByUser();
    @Query(value = "SELECT id, name\n" +
            "FROM     dbo.category\n" +
            "WHERE dbo.category.status = 1", nativeQuery = true)
    List<CategoryByName> getNameByUser();
}
