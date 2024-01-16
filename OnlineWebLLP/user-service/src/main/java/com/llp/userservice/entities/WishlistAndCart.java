package com.llp.userservice.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.llp.userservice.entities.keys.WishlistAndCartKey;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class WishlistAndCart {
    @EmbeddedId
    private WishlistAndCartKey id;
    private double defaultPrice;
    private double price;
    private boolean isWishlist;
    private boolean isCart;
    @ManyToOne
    @MapsId("userId")
    @JoinColumn(name = "userId")
    @JsonIgnore
    private User user;
}
