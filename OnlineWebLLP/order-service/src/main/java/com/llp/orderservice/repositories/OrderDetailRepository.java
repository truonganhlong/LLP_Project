package com.llp.orderservice.repositories;

import com.llp.orderservice.entities.OrderDetail;
import com.llp.orderservice.entities.keys.OrderDetailKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Repository
public interface OrderDetailRepository extends JpaRepository<OrderDetail, OrderDetailKey> {
    @Transactional
    @Modifying
    @Query(value = "INSERT INTO orderDetail (userId,courseId,orderId,price) VALUES(:userId,:courseId,:orderId,:price)", nativeQuery = true)
    void create(@Param("userId") int userId, @Param("courseId") String courseId, @Param("orderId") String orderId, @Param("price") double price);

    @Query(value = "SELECT od FROM OrderDetail od JOIN od.order o WHERE o.id = :orderId")
    List<OrderDetail> getAllByOrderId(@Param("orderId") UUID orderId);
}
