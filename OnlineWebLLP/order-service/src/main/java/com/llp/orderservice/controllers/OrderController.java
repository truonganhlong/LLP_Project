package com.llp.orderservice.controllers;

import com.llp.orderservice.services.OrderService;
import com.llp.sharedproject.exceptions.ConflictException;
import com.llp.sharedproject.exceptions.InternalServerException;
import com.llp.sharedproject.exceptions.NotFoundException;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.annotation.Nullable;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/order/order")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class OrderController {
    private final OrderService orderService;

    @Operation(summary = "Api 98: checkout")
    @RequestMapping(value = "/user/checkout", method = RequestMethod.POST)
    public ResponseEntity<?> checkout(@RequestHeader("Authorization") String authorizationHeader, @RequestParam(required = false) @Nullable String courseId, @RequestParam int paymentId){
        try {
            orderService.checkout(authorizationHeader,courseId,paymentId);
            return ResponseEntity.status(HttpStatus.CREATED).body("Submit successfully");
        } catch (ConflictException e){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        } catch (InternalServerException e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @Operation(summary = "Api 99: get all order by admin")
    @RequestMapping(value = "/admin/getAllByAdmin", method = RequestMethod.GET)
    public ResponseEntity<?> getAllByAdmin(@RequestParam(defaultValue = "0") Integer pageNo,
                                           @RequestParam(defaultValue = "20") Integer pageSize){
        try {
            var data = orderService.getAllByAdmin(pageNo,pageSize);
            return ResponseEntity.ok(data);
        } catch (InternalServerException e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @Operation(summary = "Api 100: get all order by user")
    @RequestMapping(value = "/user/getAllByUser", method = RequestMethod.GET)
    public ResponseEntity<?> getAllByUser(@RequestHeader("Authorization") String authorizationHeader,
                                           @RequestParam(defaultValue = "0") Integer pageNo,
                                           @RequestParam(defaultValue = "20") Integer pageSize){
        try {
            var data = orderService.getAllByUser(authorizationHeader,pageNo,pageSize);
            return ResponseEntity.ok(data);
        } catch (InternalServerException e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}
