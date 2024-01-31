package com.llp.courseservice.controllers;

import com.llp.courseservice.dtos.Discount.DiscountCreateRequest;
import com.llp.courseservice.services.DiscountService;
import com.llp.sharedproject.exceptions.InternalServerException;
import com.llp.sharedproject.exceptions.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/discount")
@RequiredArgsConstructor
public class DiscountController {
    private final DiscountService discountService;

    @RequestMapping(value = "/byCourseId/{courseId}", method = RequestMethod.GET)
    public ResponseEntity<?> getById(@PathVariable UUID courseId){
        try {
            var data = discountService.getByCourseId(courseId);
            return ResponseEntity.ok(data);
        } catch (NotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (InternalServerException e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> create(@RequestBody DiscountCreateRequest request){
        try {
            discountService.create(request);
            return ResponseEntity.status(HttpStatus.CREATED).body("Create successfully");
        } catch (InternalServerException e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @RequestMapping(value = "/delete/{courseId}", method = RequestMethod.DELETE)
    public ResponseEntity<?> delete(@PathVariable UUID courseId){
        try {
            discountService.delete(courseId);
            return ResponseEntity.status(HttpStatus.CREATED).body("Delete successfully");
        } catch (NotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (InternalServerException e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

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
