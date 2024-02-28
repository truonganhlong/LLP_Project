package com.llp.orderservice.controllers;

import com.llp.orderservice.services.PaymentMethodService;
import com.llp.sharedproject.exceptions.InternalServerException;
import com.llp.sharedproject.exceptions.NotFoundException;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/order/paymentMethod")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class PaymentMethodController {
    private final PaymentMethodService paymentMethodService;
    @Operation(summary = "Api 82: get all payment method")
    @RequestMapping(value = "/public/all", method = RequestMethod.GET)
    public ResponseEntity<?> getAll(){
        try {
            var data = paymentMethodService.getAll();
            return ResponseEntity.ok(data);
        } catch (InternalServerException e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
    @Operation(summary = "Api 83: get payment method by id")
    @RequestMapping(value = "/public/byId/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getById(@PathVariable int id){
        try {
            var data = paymentMethodService.getById(id);
            return ResponseEntity.ok(data);
        } catch (NotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (InternalServerException e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}
