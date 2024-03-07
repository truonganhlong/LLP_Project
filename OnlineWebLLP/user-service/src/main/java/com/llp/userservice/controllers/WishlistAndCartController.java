package com.llp.userservice.controllers;

import com.llp.sharedproject.exceptions.ConflictException;
import com.llp.sharedproject.exceptions.InternalServerException;
import com.llp.sharedproject.exceptions.NotFoundException;
import com.llp.userservice.entities.User;
import com.llp.userservice.services.WishlistAndCartService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/user/wac")
@RequiredArgsConstructor
@PreAuthorize("hasRole('USER')")
@CrossOrigin(origins = "http://localhost:3000")
public class WishlistAndCartController {
    private final WishlistAndCartService wishlistAndCartService;

    @Operation(summary = "Api 91: add course to wishlist")
    @RequestMapping(value = "/addToWishlist", method = RequestMethod.POST)
    public ResponseEntity<?> addToWishlist(Authentication authentication, @RequestParam String courseId){
        try {
            if (authentication != null && authentication.getPrincipal() instanceof User) {
                User user = (User) authentication.getPrincipal();
                int userId = user.getId().intValue();
                wishlistAndCartService.addToWishlist(userId, courseId);
                return ResponseEntity.status(HttpStatus.CREATED).body("Add course successfully");
            }
            else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
            }
        } catch (ConflictException e){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        } catch (InternalServerException e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @Operation(summary = "Api 92: add course to cart")
    @RequestMapping(value = "/addToCart", method = RequestMethod.POST)
    public ResponseEntity<?> addToCart(Authentication authentication, @RequestParam String courseId){
        try {
            if (authentication != null && authentication.getPrincipal() instanceof User) {
                User user = (User) authentication.getPrincipal();
                int userId = user.getId().intValue();
                wishlistAndCartService.addToCart(userId, courseId);
                return ResponseEntity.status(HttpStatus.CREATED).body("Add course successfully");
            }
            else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
            }
        } catch (ConflictException e){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        } catch (InternalServerException e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @Operation(summary = "Api 93: move course to wishlist or cart")
    @RequestMapping(value = "/moveToWishlistOrCart", method = RequestMethod.PUT)
    public ResponseEntity<?> moveToWishlistOrCart(Authentication authentication, @RequestParam String courseId){
        try {
            if (authentication != null && authentication.getPrincipal() instanceof User) {
                User user = (User) authentication.getPrincipal();
                int userId = user.getId().intValue();
                wishlistAndCartService.moveToWishlistOrCart(userId, courseId);
                return ResponseEntity.status(HttpStatus.CREATED).body("Move course successfully");
            }
            else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
            }
        } catch (NotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (InternalServerException e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @Operation(summary = "Api 94: remove course from wishlist or cart")
    @RequestMapping(value = "/removeFromWishlistAndCart", method = RequestMethod.DELETE)
    public ResponseEntity<?> removeFromWishlistAndCart(Authentication authentication, @RequestParam String courseId){
        try {
            if (authentication != null && authentication.getPrincipal() instanceof User) {
                User user = (User) authentication.getPrincipal();
                int userId = user.getId().intValue();
                wishlistAndCartService.removeFromWishlistAndCart(userId, courseId);
                return ResponseEntity.status(HttpStatus.CREATED).body("Move course successfully");
            }
            else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
            }
        } catch (NotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (InternalServerException e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @Operation(summary = "Api 95: get wishlist")
    @RequestMapping(value = "/wishlist", method = RequestMethod.GET)
    public ResponseEntity<?> getWishlist(Authentication authentication){
        try {
            if (authentication != null && authentication.getPrincipal() instanceof User) {
                User user = (User) authentication.getPrincipal();
                int userId = user.getId().intValue();
                var data = wishlistAndCartService.getWishlist(userId);
                return ResponseEntity.ok(data);
            }
            else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
            }
        } catch (InternalServerException e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @Operation(summary = "Api 96: get cart")
    @RequestMapping(value = "/cart", method = RequestMethod.GET)
    public ResponseEntity<?> getCart(Authentication authentication){
        try {
            if (authentication != null && authentication.getPrincipal() instanceof User) {
                User user = (User) authentication.getPrincipal();
                int userId = user.getId().intValue();
                var data = wishlistAndCartService.getCart(userId);
                return ResponseEntity.ok(data);
            }
            else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
            }
        } catch (InternalServerException e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}
