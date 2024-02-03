package com.llp.userservice.entities.keys;

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
public class WishlistAndCartKey implements Serializable {
    @Column(name = "courseId")
    private UUID courseId;
    @Column(name = "userId")
    private Long userId;
}
