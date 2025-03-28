package com.example.pironeer.repository;

import com.example.pironeer.domain.Order;
import com.example.pironeer.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByUserId(Long userId);
    List<Order> findByUser(User user);
}
