package com.llp.courseservice.services;

import com.llp.courseservice.dtos.Discount.DiscountCreateRequest;
import com.llp.courseservice.dtos.Discount.DiscountResponse;

import java.util.UUID;

public interface DiscountService {
    void create(DiscountCreateRequest request);
    void delete(UUID courseId);
    DiscountResponse getByCourseId(UUID courseId);
    double returnDiscountPrice(UUID courseId);
    void end();
}
