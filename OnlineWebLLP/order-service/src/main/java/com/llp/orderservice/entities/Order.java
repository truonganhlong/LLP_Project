package com.llp.orderservice.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private Long userId;
    private double totalPrice;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime orderTime;
    @ManyToOne
    @JoinColumn(name = "paymentId", nullable = false)
    @JsonIgnore
    private PaymentMethod paymentMethod;
    @OneToMany(mappedBy = "order")
    private List<OrderDetail> orderDetails;
}
