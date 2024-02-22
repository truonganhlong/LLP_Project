package com.llp.userservice.dtos.wishlistAndCart;

import com.llp.userservice.clients.dtos.CourseCardResponse;
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
