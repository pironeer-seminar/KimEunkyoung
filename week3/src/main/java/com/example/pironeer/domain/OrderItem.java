package com.example.pironeer.domain;

import jakarta.persistence.*;

@Entity
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private int quantity;      // 주문 수량
    private int orderPrice;    // 주문 당시 상품 가격

    @ManyToOne
    private Order order;

    @ManyToOne
    private Product product;

    protected OrderItem() {}

    public OrderItem(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
        this.orderPrice = product.getPrice();  // 주문 당시 가격 저장
        product.removeAmount(quantity);        // 재고 감소 (0 미만 예외 발생)
    }

    // Getter/Setter
    public void setOrder(Order order) {
        this.order = order;
    }

    public Product getProduct() {
        return product;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getOrderPrice() {
        return orderPrice;
    }
}
