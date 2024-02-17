package com.llp.orderservice.services;

import com.llp.orderservice.dtos.paymentMethod.PaymentMethodResponse;

import java.util.List;

public interface PaymentMethodService {
    List<PaymentMethodResponse> getAll();
    PaymentMethodResponse getById(int id);
}
