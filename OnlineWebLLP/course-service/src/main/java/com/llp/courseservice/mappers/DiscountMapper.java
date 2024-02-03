package com.llp.courseservice.mappers;

import com.llp.courseservice.dtos.Discount.DiscountResponse;
import com.llp.courseservice.entities.Discount;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DiscountMapper {
    public static DiscountResponse convertToResponse(Discount discount){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return DiscountResponse.builder()
                .id(discount.getId())
                .discountValue(discount.getDiscountValue())
                .startTime(discount.getStartTime().format(formatter))
                .endTime(discount.getEndTime().format(formatter))
                .build();
    }
}
