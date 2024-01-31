package com.llp.courseservice.dtos.Discount;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class DiscountResponse {
    private Long id;
    private double discountValue;
    private String startTime;
    private String endTime;
}
