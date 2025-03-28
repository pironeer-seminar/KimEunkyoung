package com.example.pironeer.controller;

import com.example.pironeer.domain.Order;
import com.example.pironeer.service.OrderRequestItem;
import com.example.pironeer.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;

    // 주문 생성
    @PostMapping("/{userId}")
    public Order createOrder(
            @PathVariable Long userId,
            @RequestBody List<OrderRequestItem> requestItems
    ) {
        return orderService.createOrder(userId, requestItems);
    }

    // 전체 주문 조회
    @GetMapping
    public List<Order> getAllOrders() {
        return orderService.getAllOrders();
    }

    // 특정 주문 조회
    @GetMapping("/{orderId}")
    public Order getOrder(@PathVariable Long orderId) {
        return orderService.getOrder(orderId);
    }

    // 주문 취소
    @PostMapping("/{orderId}/cancel")
    public void cancelOrder(@PathVariable Long orderId) {
        orderService.cancelOrder(orderId);
    }

    // 특정 유저의 주문 전체 조회
    @GetMapping("/user/{userId}")
    public List<Order> getUserOrders(@PathVariable Long userId) {
        return orderService.getOrdersByUser(userId);
    }
}
