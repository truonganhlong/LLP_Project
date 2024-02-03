package com.llp.courseservice.repositories;

import com.llp.courseservice.entities.Discount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface DiscountRepository extends JpaRepository<Discount, Long> {
    Discount getByCourseId(UUID courseId);
}
