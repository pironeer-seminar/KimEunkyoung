package com.example.pironeer.domain;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long id;

    @ManyToOne
    public User user;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    public List<OrderItem> orderItems = new ArrayList<>();

    public String status; // "ORDERED", "CANCELLED"
    public LocalDateTime orderDate;

    protected Order() {}

    public Order(User user, List<OrderItem> orderItems, String status) {
        this.user = user;
        this.status = status;
        this.orderDate = LocalDateTime.now();
        this.orderItems = orderItems;

        for (OrderItem item : orderItems) {
            item.setOrder(this); // 양방향 매핑
        }
    }

    public void cancel() {
        if (this.status.equals("CANCELLED")) return;
        this.status = "CANCELLED";
        for (OrderItem item : orderItems) {
            item.getProduct().addAmount(item.getQuantity()); // 재고 복구
        }
    }

    // Getter
    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public String getStatus() {
        return status;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }
}