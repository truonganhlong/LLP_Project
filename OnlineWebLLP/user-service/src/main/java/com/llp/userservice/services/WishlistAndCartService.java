package com.llp.userservice.services;

import com.llp.userservice.clients.dtos.CourseCardResponse;
import com.llp.userservice.dtos.wishlistAndCart.WishlistAndCartResponse;

import java.util.List;

public interface WishlistAndCartService {
    void addToWishlist(int userId, String courseId);
    void addToCart(int userId, String courseId);
    void moveToWishlistOrCart(int userId, String courseId);
    void removeFromWishlistAndCart(int userId, String courseId);
    List<CourseCardResponse> getWishlist(int userId);
    WishlistAndCartResponse getCart(int userId);
}
