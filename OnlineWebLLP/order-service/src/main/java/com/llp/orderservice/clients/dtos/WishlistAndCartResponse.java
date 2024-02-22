package com.llp.orderservice.clients.dtos;

import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class WishlistAndCartResponse {
    private List<CourseCardResponse> courseCardResponses;
    private double totalPrice;
    private double totalDiscountPrice;
}
