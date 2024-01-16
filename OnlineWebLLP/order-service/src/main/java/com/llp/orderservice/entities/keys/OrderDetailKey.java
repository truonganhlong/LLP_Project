package com.llp.orderservice.entities.keys;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.UUID;

@Embeddable
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetailKey implements Serializable {
    @Column(name = "userId")
    private Long userId;
    @Column(name = "courseId")
    private UUID courseId;
}
