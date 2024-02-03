package com.llp.courseservice.controllers;

import com.llp.courseservice.dtos.Discount.DiscountCreateRequest;
import com.llp.courseservice.services.DiscountService;
import com.llp.sharedproject.exceptions.InternalServerException;
import com.llp.sharedproject.exceptions.NotFoundException;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/course/discount")
@RequiredArgsConstructor
public class DiscountController {
    private final DiscountService discountService;
    @Operation(summary = "Api 18: get discount of course by courseId")
    @RequestMapping(value = "/byCourseId/{courseId}", method = RequestMethod.GET)
    public ResponseEntity<?> getById(@PathVariable String courseId){
        try {
            var data = discountService.getByCourseId(courseId);
            return ResponseEntity.ok(data);
        } catch (NotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (InternalServerException e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
    @Operation(summary = "Api 19: create new discount for course", description = "Discount value is int (ex: 80 in 80%), startTime and endTime must solve in FE to greater than today")
    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> create(@RequestBody DiscountCreateRequest request){
        try {
            discountService.create(request);
            return ResponseEntity.status(HttpStatus.CREATED).body("Create successfully");
        } catch (InternalServerException e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
    @Operation(summary = "Api 20: delete course's discount by courseId")
    @RequestMapping(value = "/delete/{courseId}", method = RequestMethod.DELETE)
    public ResponseEntity<?> delete(@PathVariable String courseId){
        try {
            discountService.delete(courseId);
            return ResponseEntity.status(HttpStatus.CREATED).body("Delete successfully");
        } catch (NotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (InternalServerException e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
    @Operation(summary = "Api 21: end the discount time", description = "when it pass to other day (datetime = 12 a.m), FE call this api to check if discount expired then this api auto delete the discount")
    @RequestMapping(value = "/end", method = RequestMethod.DELETE)
    public ResponseEntity<?> end(){
        try {
            discountService.end();
            return ResponseEntity.status(HttpStatus.CREATED).body("Delete successfully");
        } catch (InternalServerException e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}
