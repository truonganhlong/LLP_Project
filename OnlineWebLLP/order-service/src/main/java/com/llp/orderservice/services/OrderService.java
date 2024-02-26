package com.llp.orderservice.services;

import com.llp.orderservice.dtos.order.OrderResponse;

import java.util.List;

public interface OrderService {
    void checkout(String authorizationHeader, String courseId, int paymentId);

    List<OrderResponse> getAllByAdmin(Integer pageNo, Integer pageSize);
    List<OrderResponse> getAllByUser(String authorizationHeader, Integer pageNo, Integer pageSize);
}
