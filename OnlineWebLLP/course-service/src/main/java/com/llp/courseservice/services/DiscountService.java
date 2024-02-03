package com.llp.courseservice.services;

import com.llp.courseservice.dtos.Discount.DiscountCreateRequest;
import com.llp.courseservice.dtos.Discount.DiscountResponse;

import java.util.UUID;

public interface DiscountService {
    void create(DiscountCreateRequest request);
    void delete(String courseId);
    DiscountResponse getByCourseId(String courseId);
    double returnDiscountPrice(String courseId);
    void end();
}
