package com.example.pironeer.service;

import com.example.pironeer.domain.*;
import com.example.pironeer.repository.OrderRepository;
import com.example.pironeer.repository.ProductRepository;
import com.example.pironeer.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final UserService userService;

    @Transactional
    public Order createOrder(Long userId, List<OrderRequestItem> requestItems) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("유저를 찾을 수 없습니다."));

        List<OrderItem> orderItems = new ArrayList<>();

        for (OrderRequestItem request : requestItems) {
            Product product = productRepository.findById(request.productId())
                    .orElseThrow(() -> new IllegalArgumentException("상품을 찾을 수 없습니다."));
            OrderItem item = new OrderItem(product, request.amount());
            orderItems.add(item);
        }

        Order order = new Order(user, orderItems, "ORDERED");
        return orderRepository.save(order);
    }

    public Order getOrder(Long id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("주문을 찾을 수 없습니다."));
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public List<Order> getOrdersByUser(Long userId) {
        return orderRepository.findByUserId(userId);
    }

    public List<Order> getOrdersByUserId(Long userId) {
        User user = userService.getUser(userId);
        return orderRepository.findByUser(user);
    }


    @Transactional
    public void cancelOrder(Long orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new IllegalArgumentException("주문을 찾을 수 없습니다."));
        order.cancel();  // 상태 변경 + 재고 복구
    }
}
