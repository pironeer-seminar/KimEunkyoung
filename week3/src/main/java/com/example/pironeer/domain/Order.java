package com.example.pironeer.domain;

import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders")
public class Order {

    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long id;

    @ManyToOne
    public User user;

    // Getter
    @Getter
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    public List<OrderItem> orderItems = new ArrayList<>();

    @Getter
    public String status; // "ORDERED", "CANCELLED"
    @Getter
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
        if (this.status.equals("CANCELED")) {
            throw new IllegalStateException("이미 취소된 주문입니다.");
        }
        this.status = "CANCELED";
        for (OrderItem item : orderItems) {
            item.getProduct().addAmount(item.getQuantity());
        }
    }

}