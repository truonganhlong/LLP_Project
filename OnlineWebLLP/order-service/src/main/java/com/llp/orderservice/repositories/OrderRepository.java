package com.llp.orderservice.repositories;

import com.llp.orderservice.entities.Order;
import feign.Param;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface OrderRepository extends PagingAndSortingRepository<Order, UUID>,JpaRepository<Order, UUID>{
    @Query(value = "SELECT o FROM Order o")
    List<Order> getAllByAdmin(Pageable pageable);
    @Query(value = "SELECT o FROM Order o WHERE o.userId = :userId ORDER BY o.orderTime DESC")
    List<Order> getAllByUser(@Param("userId") int userId, Pageable pageable);
}
