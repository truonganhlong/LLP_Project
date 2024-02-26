package com.llp.userservice.services.impl;

import com.llp.sharedproject.exceptions.InternalServerException;
import com.llp.sharedproject.exceptions.NotFoundException;
import com.llp.userservice.clients.CourseClient;
import com.llp.userservice.clients.dtos.CourseCardResponse;
import com.llp.userservice.dtos.wishlistAndCart.WishlistAndCartResponse;
import com.llp.userservice.entities.WishlistAndCart;
import com.llp.userservice.repositories.WishlistAndCartRepository;
import com.llp.userservice.services.WishlistAndCartService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class WishlistAndCartServiceImpl implements WishlistAndCartService {
    private final WishlistAndCartRepository wishlistAndCartRepository;
    private final CourseClient courseClient;
    @Override
    public void addToWishlist(int userId, String courseId) {
        try {
            wishlistAndCartRepository.addToWishlist(courseId,userId);
        } catch (Exception e){
            throw new InternalServerException("Server Error");
        }
    }

    @Override
    public void addToCart(int userId, String courseId) {
        try {
            wishlistAndCartRepository.addToCart(courseId,userId);
        } catch (Exception e){
            throw new InternalServerException("Server Error");
        }
    }

    @Override
    public void moveToWishlistOrCart(int userId, String courseId) {
        try {
            WishlistAndCart wishlistAndCart = wishlistAndCartRepository.getById(courseId,userId);
            if(Objects.isNull(wishlistAndCart)){
                throw new NotFoundException("Not found in database");
            }
            if(wishlistAndCart.isCart()){
                wishlistAndCart.setCart(false);
                wishlistAndCart.setWishlist(true);
            } else {
                wishlistAndCart.setCart(true);
                wishlistAndCart.setWishlist(false);
            }
            wishlistAndCartRepository.save(wishlistAndCart);
        } catch (NotFoundException e){
            throw new NotFoundException(e.getMessage());
        } catch (Exception e){
            throw new InternalServerException("Server Error");
        }
    }

    @Override
    public void removeFromWishlistAndCart(int userId, String courseId) {
        try {
            WishlistAndCart wishlistAndCart = wishlistAndCartRepository.getById(courseId,userId);
            if(!Objects.isNull(wishlistAndCart)){
                wishlistAndCartRepository.delete(wishlistAndCart);
            }
        } catch (NotFoundException e){
            throw new NotFoundException(e.getMessage());
        } catch (Exception e){
            throw new InternalServerException("Server Error");
        }
    }

    @Override
    public List<CourseCardResponse> getWishlist(int userId) {
        try {
            List<CourseCardResponse> data = new ArrayList<>();
            List<WishlistAndCart> wishlistAndCarts = wishlistAndCartRepository.getWishlist(userId);
            for (var wishlistAndCart:wishlistAndCarts) {
                CourseCardResponse courseCardResponse = courseClient.getCourseCardById(wishlistAndCart.getId().getCourseId());
                data.add(courseCardResponse);
            }
            return  data;
        } catch (Exception e){
            throw new InternalServerException("Server Error");
        }
    }

    @Override
    public WishlistAndCartResponse getCart(int userId) {
        try {
            double totalPrice = 0;
            double totalDiscountPrice = 0;
            WishlistAndCartResponse data = new WishlistAndCartResponse();
            List<CourseCardResponse> courseCardResponses = new ArrayList<>();
            List<WishlistAndCart> wishlistAndCarts = wishlistAndCartRepository.getCart(userId);
            for (var wishlistAndCart:wishlistAndCarts) {
                CourseCardResponse courseCardResponse = courseClient.getCourseCardById(wishlistAndCart.getId().getCourseId());
                courseCardResponses.add(courseCardResponse);
                totalPrice += courseCardResponse.getPrice();
                totalDiscountPrice += courseCardResponse.getDiscountPrice();
            }
            data.setCourseCardResponses(courseCardResponses);
            data.setTotalPrice(totalPrice);
            data.setTotalDiscountPrice(totalDiscountPrice);
            return data;
        } catch (Exception e){
            throw new InternalServerException("Server Error");
        }
    }
}
