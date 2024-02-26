package com.llp.orderservice.clients;

import com.llp.orderservice.clients.dtos.CourseCardResponse;
import com.llp.orderservice.clients.dtos.UserResponse;
import com.llp.orderservice.clients.dtos.WishlistAndCartResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(value= "USERS", url = "http://localhost:8222")
public interface UserClient {
    @RequestMapping(value = "/api/user/wac/cart", method = RequestMethod.GET)
    WishlistAndCartResponse getCart(@RequestHeader("Authorization") String authorizationHeader);

    @RequestMapping(value = "/api/user/yourCourse", method = RequestMethod.POST)
    void create(@RequestHeader("Authorization") String authorizationHeader, @RequestParam String courseId, @RequestParam String orderId);

    @RequestMapping(value = "/api/user/user/returnUserId", method = RequestMethod.GET)
    int returnUserId(@RequestHeader("Authorization") String authorizationHeader);

    @RequestMapping(value = "/api/user/wac/removeFromWishlistAndCart", method = RequestMethod.DELETE)
    void removeFromWishlistAndCart(@RequestHeader("Authorization") String authorizationHeader, @RequestParam String courseId);

    @RequestMapping(value = "/api/user/user/public/userInformation/{userId}", method = RequestMethod.GET)
    UserResponse getOtherUserInformation(@PathVariable int userId);

}
