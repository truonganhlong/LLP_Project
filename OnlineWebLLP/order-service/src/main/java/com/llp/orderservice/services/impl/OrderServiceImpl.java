package com.llp.orderservice.services.impl;

import com.llp.orderservice.clients.CourseClient;
import com.llp.orderservice.clients.UserClient;
import com.llp.orderservice.clients.dtos.CourseCardResponse;
import com.llp.orderservice.clients.dtos.WishlistAndCartResponse;
import com.llp.orderservice.dtos.order.OrderDetailResponse;
import com.llp.orderservice.dtos.order.OrderResponse;
import com.llp.orderservice.entities.Order;
import com.llp.orderservice.entities.OrderDetail;
import com.llp.orderservice.repositories.OrderDetailRepository;
import com.llp.orderservice.repositories.OrderRepository;
import com.llp.orderservice.repositories.PaymentMethodRepository;
import com.llp.orderservice.services.OrderService;
import com.llp.sharedproject.exceptions.InternalServerException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final OrderDetailRepository orderDetailRepository;
    private final PaymentMethodRepository paymentMethodRepository;
    private final UserClient userClient;
    private final CourseClient courseClient;
    @Override
    public void checkout(String authorizationHeader, String courseId, int paymentId) {
        try {
            if(courseId == null){
                WishlistAndCartResponse cart = userClient.getCart(authorizationHeader);
                int userId = userClient.returnUserId(authorizationHeader);
                Order order = Order.builder()
                        .userId(((long) userId))
                        .orderTime(LocalDateTime.now())
                        .paymentMethod(paymentMethodRepository.getById(paymentId))
                        .build();
                order.setTotalPrice(cart.getTotalDiscountPrice());
                orderRepository.save(order);
                for (var course:cart.getCourseCardResponses()) {
                    //create order detail
                    orderDetailRepository.create(userId,course.getId().toString(),order.getId().toString(),course.getDiscountPrice());
                    //add data to your course
                    userClient.create(authorizationHeader,course.getId().toString(),order.getId().toString());
                    //update sale num in course after checkout
                    courseClient.updateSaleNum(String.valueOf(course.getId()));
                    //remove cart
                    userClient.removeFromWishlistAndCart(authorizationHeader,course.getId().toString());
                }
            } else {
                CourseCardResponse course = courseClient.getCourseCardById(courseId);
                int userId = userClient.returnUserId(authorizationHeader);
                Order order = Order.builder()
                        .userId(((long) userId))
                        .orderTime(LocalDateTime.now())
                        .paymentMethod(paymentMethodRepository.getById(paymentId))
                        .build();
                order.setTotalPrice(course.getDiscountPrice());
                orderRepository.save(order);
                //create order detail
                orderDetailRepository.create(userId,course.getId().toString(),order.getId().toString(),course.getDiscountPrice());
                //add data to your course
                userClient.create(authorizationHeader,course.getId().toString(),order.getId().toString());
                //update sale num in course after checkout
                courseClient.updateSaleNum(String.valueOf(course.getId()));
                //remove cart
                userClient.removeFromWishlistAndCart(authorizationHeader,course.getId().toString());
            }

        } catch (Exception e){
            throw new InternalServerException("Server Error");
        }
    }

    @Override
    public List<OrderResponse> getAllByAdmin(Integer pageNo, Integer pageSize) {
        try {
            Pageable paging = PageRequest.of(pageNo, pageSize);
            List<Order> orders = orderRepository.getAllByAdmin(paging);
            return getOrderResponses(orders);
        } catch (Exception e){
            throw new InternalServerException("Server Error");
        }
    }

    @Override
    public List<OrderResponse> getAllByUser(String authorizationHeader, Integer pageNo, Integer pageSize) {
        try {
            Pageable paging = PageRequest.of(pageNo, pageSize);
            int userId = userClient.returnUserId(authorizationHeader);
            List<Order> orders = orderRepository.getAllByUser(userId,paging);
            return getOrderResponses(orders);
        } catch (Exception e){
            throw new InternalServerException("Server Error");
        }
    }
//---------------------------------------------------------------------------------------------------------------------------
    private List<OrderResponse> getOrderResponses(List<Order> orders) {
        List<OrderResponse> data = new ArrayList<>();
        for (var order:orders) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMMM yyyy hh:mm");
            String orderTime = order.getOrderTime().format(formatter);
            OrderResponse orderResponse = OrderResponse.builder()
                    .id(order.getId())
                    .user(userClient.getOtherUserInformation(order.getUserId().intValue()))
                    .totalPrice(order.getTotalPrice())
                    .orderTime(orderTime)
                    .paymentMethod(order.getPaymentMethod().getName())
                    .build();
            List<OrderDetailResponse> orderDetailResponses = new ArrayList<>();
            List<OrderDetail> orderDetails = orderDetailRepository.getAllByOrderId(order.getId());
            for (var orderDetail:orderDetails) {
                OrderDetailResponse orderDetailResponse = OrderDetailResponse.builder()
                        .course(courseClient.getCourseCardById(orderDetail.getId().getCourseId()))
                        .buyPrice(orderDetail.getPrice())
                        .build();
                orderDetailResponses.add(orderDetailResponse);
            }
            orderResponse.setOrderDetails(orderDetailResponses);
            data.add(orderResponse);
        }
        return data;
    }
}
