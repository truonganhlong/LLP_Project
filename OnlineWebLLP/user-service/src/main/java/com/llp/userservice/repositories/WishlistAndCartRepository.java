package com.llp.userservice.repositories;

import com.llp.userservice.entities.WishlistAndCart;
import com.llp.userservice.entities.keys.WishlistAndCartKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface WishlistAndCartRepository extends JpaRepository<WishlistAndCart, WishlistAndCartKey> {
    @Transactional
    @Modifying
    @Query(value = "INSERT INTO wishlistAndCart (courseId,userId,isWishlist,isCart) VALUES(:courseId,:userId,1,0)", nativeQuery = true)
    void addToWishlist(@Param("courseId") String courseId, @Param("userId") int userId);

    @Transactional
    @Modifying
    @Query(value = "INSERT INTO wishlistAndCart (courseId,userId,isWishlist,isCart) VALUES(:courseId,:userId,0,1)", nativeQuery = true)
    void addToCart(@Param("courseId") String courseId, @Param("userId") int userId);

    @Query(value = "SELECT wishlistAndCart.*\n" +
            "FROM     wishlistAndCart\n" +
            "WHERE wishlistAndCart.courseId = :courseId AND wishlistAndCart.userId = :userId", nativeQuery = true)
    WishlistAndCart getById(@Param("courseId") String courseId, @Param("userId") int userId);

    @Query(value = "SELECT wishlistAndCart.*\n" +
            "FROM     wishlistAndCart\n" +
            "WHERE wishlistAndCart.userId = :userId\n" +
            "AND wishlistAndCart.isWishlist = 1", nativeQuery = true)
    List<WishlistAndCart> getWishlist(@Param("userId") int userId);

    @Query(value = "SELECT wishlistAndCart.*\n" +
            "FROM     wishlistAndCart\n" +
            "WHERE wishlistAndCart.userId = :userId\n" +
            "AND wishlistAndCart.isCart = 1", nativeQuery = true)
    List<WishlistAndCart> getCart(@Param("userId") int userId);
}
