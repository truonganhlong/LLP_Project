package com.llp.orderservice.dtos.order;

import com.llp.orderservice.clients.dtos.CourseCardResponse;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class OrderDetailResponse {
    private CourseCardResponse course;
    private double buyPrice;
}
