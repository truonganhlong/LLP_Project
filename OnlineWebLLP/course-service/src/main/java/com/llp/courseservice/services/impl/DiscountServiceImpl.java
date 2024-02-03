package com.llp.courseservice.services.impl;

import com.llp.courseservice.dtos.Discount.DiscountCreateRequest;
import com.llp.courseservice.dtos.Discount.DiscountResponse;
import com.llp.courseservice.entities.Discount;
import com.llp.courseservice.mappers.DiscountMapper;
import com.llp.courseservice.repositories.CourseRepository;
import com.llp.courseservice.repositories.DiscountRepository;
import com.llp.courseservice.services.DiscountService;
import com.llp.sharedproject.exceptions.InternalServerException;
import com.llp.sharedproject.exceptions.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DiscountServiceImpl implements DiscountService {
    private final DiscountRepository discountRepository;
    private final CourseRepository courseRepository;

    @Override
    public void create(DiscountCreateRequest request) {
        try {
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            Discount discount = Discount.builder()
                    .discountValue((double) request.getDiscountValue()/100)
                    .startTime(LocalDate.parse(request.getStartTime(), dateFormatter))
                    .endTime(LocalDate.parse(request.getEndTime(), dateFormatter))
                    .course(courseRepository.getById(UUID.fromString(request.getCourseId())))
                    .build();
            discountRepository.save(discount);
        } catch (Exception e){
            throw new InternalServerException("Server Error");
        }
    }

    @Override
    public void delete(String courseId) {
        try {
            Discount discount = discountRepository.getByCourseId(UUID.fromString(courseId));
            if(Objects.isNull(discount)){
                throw new NotFoundException("Not found in database");
            }
            discountRepository.delete(discount);
        } catch (NotFoundException e){
            throw new NotFoundException(e.getMessage());
        } catch (Exception e){
            throw new InternalServerException("Server Error");
        }
    }

    @Override
    public DiscountResponse getByCourseId(String courseId) {
        try {
            Discount discount = discountRepository.getByCourseId(UUID.fromString(courseId));
            if(Objects.isNull(discount)){
                throw new NotFoundException("Not found in database");
            }
            return DiscountMapper.convertToResponse(discount);
        } catch (NotFoundException e){
            throw new NotFoundException(e.getMessage());
        } catch (Exception e){
            throw new InternalServerException("Server Error");
        }
    }

    @Override
    public double returnDiscountPrice(String courseId) {
        try {
            Discount discount = discountRepository.getByCourseId(UUID.fromString(courseId));
            if(Objects.isNull(discount)){
                return 0;
            }
            else {
                double discountValue = discount.getDiscountValue();
                double price = courseRepository.getById(UUID.fromString(courseId)).getPrice();
                return price * discountValue;
            }
        } catch (Exception e){
            throw new InternalServerException("Server Error");
        }
    }

    @Override
    public void end() {
        try {
            List<Discount> discounts = discountRepository.findAll();
            LocalDate currentDate = LocalDate.now();
            for (var discount:discounts) {
                if(currentDate.equals(discount.getEndTime())){
                    discountRepository.delete(discount);
                }
            }
        } catch (Exception e){
            throw new InternalServerException("Server Error");
        }
    }
}
