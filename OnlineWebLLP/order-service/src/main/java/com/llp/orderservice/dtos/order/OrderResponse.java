package com.llp.orderservice.dtos.order;

import com.llp.orderservice.clients.dtos.UserResponse;
import lombok.*;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class OrderResponse {
    private UUID id;
    private UserResponse user;
    private double totalPrice;
    private String orderTime;
    private String paymentMethod;
    private List<OrderDetailResponse> orderDetails;
}
