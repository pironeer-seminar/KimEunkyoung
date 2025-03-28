package com.example.pironeer;

import com.example.pironeer.domain.*;
import com.example.pironeer.repository.*;
import com.example.pironeer.service.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Transactional
public class OrderCancellationTest {

    @Autowired
    OrderService orderService;
    @Autowired
    UserService userService;
    @Autowired
    ProductService productService;
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    ProductRepository productRepository;
    @PersistenceContext
    EntityManager em;
    private Long savedUserId;
    private Long keyboardId;
    private Long mouseId;

    @BeforeEach
    void setUp() {
        User user = new User("CancelTester", "canceltester@example.com");
        savedUserId = userService.createUser(user);

        Product keyboard = new Product("Keyboard", 30_000, 5);
        Product mouse = new Product("Mouse", 20_000, 5);
        keyboardId = productService.createProduct(keyboard);
        mouseId = productService.createProduct(mouse);
    }

    @Test
    @DisplayName("주문을 취소하면 주문 상태가 'CANCELED'가 되고, 재고가 복원되어야 한다.")
    void cancelOrderTest() {
        OrderRequestItem item1 = new OrderRequestItem(keyboardId, 2);
        OrderRequestItem item2 = new OrderRequestItem(mouseId, 1);
        Order order1 = orderService.createOrder(savedUserId, List.of(item1));
        Order order2 = orderService.createOrder(savedUserId, List.of(item2));
        Long orderId = order1.getId();
        Long orderId2 = order2.getId();

        em.flush();
        em.clear();

        Order orderBeforeCancel = orderRepository.findById(orderId).orElse(null);
        assertThat(orderBeforeCancel).isNotNull();
        //assertThat(orderBeforeCancel.getStatus()).isEqualTo("ORDERED");
        assertThat(orderBeforeCancel.getStatus()).isEqualTo("ORDERED");

        Product keyboardBeforeCancel = productRepository.findById(keyboardId).orElse(null);
        Product mouseBeforeCancel = productRepository.findById(mouseId).orElse(null);
        assertThat(keyboardBeforeCancel.getStockQuantity()).isEqualTo(3);
        assertThat(mouseBeforeCancel.getStockQuantity()).isEqualTo(4);

        orderService.cancelOrder(orderId);
        orderService.cancelOrder(orderId2);
        em.flush();
        em.clear();

        Order orderAfterCancel = orderRepository.findById(orderId).orElse(null);
        //assertThat(orderAfterCancel.getStatus()).isEqualTo("CANCELED");
        assertThat(orderAfterCancel.getStatus()).isEqualTo("CANCELED");

        Product keyboardAfterCancel = productRepository.findById(keyboardId).orElse(null);
        Product mouseAfterCancel = productRepository.findById(mouseId).orElse(null);
        assertThat(keyboardAfterCancel.getStockQuantity()).isEqualTo(5);
        assertThat(mouseAfterCancel.getStockQuantity()).isEqualTo(5);
    }

    @Test
    @DisplayName("이미 취소된 주문(상태 'CANCELED')을 다시 취소하려고 하면 예외 발생")
    void cancelAlreadyCanceledOrderTest() {
        OrderRequestItem item = new OrderRequestItem(keyboardId, 1);
        Order order = orderService.createOrder(savedUserId, List.of(item));
        Long orderId = order.getId();

        orderService.cancelOrder(orderId);
        em.flush();
        em.clear();

        assertThrows(IllegalStateException.class, () -> {
            orderService.cancelOrder(orderId);
        });
    }
}
