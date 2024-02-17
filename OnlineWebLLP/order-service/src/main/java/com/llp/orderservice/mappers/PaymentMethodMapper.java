package com.llp.orderservice.mappers;

import com.llp.orderservice.dtos.paymentMethod.PaymentMethodResponse;
import com.llp.orderservice.entities.PaymentMethod;

public class PaymentMethodMapper {
    public static PaymentMethodResponse convertToResponse(PaymentMethod paymentMethod){
        return PaymentMethodResponse.builder()
                .id(paymentMethod.getId())
                .name(paymentMethod.getName())
                .build();
    }
}
