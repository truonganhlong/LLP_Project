package com.llp.orderservice.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.llp.orderservice.entities.keys.OrderDetailKey;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Table(name = "orderDetail")
public class OrderDetail {
    @EmbeddedId
    private OrderDetailKey id;
    private double defaultPrice;
    private double price;
    private boolean status;
    @ManyToOne
    @JoinColumn(name = "orderId")
    @JsonIgnore
    private Order order;
}
