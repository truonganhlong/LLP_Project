package com.llp.orderservice.dtos.paymentMethod;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class PaymentMethodResponse {
    private Long id;
    private String name;
}
