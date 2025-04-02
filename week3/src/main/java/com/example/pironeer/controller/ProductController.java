package com.example.pironeer.controller;

import com.example.pironeer.domain.Product;
import com.example.pironeer.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    // 상품 등록
    @PostMapping
    public Product registerProduct(@RequestParam String name,
                                   @RequestParam int price,
                                   @RequestParam int amount) {
        return productService.register(name, price, amount);
    }

    // 전체 상품 조회
    @GetMapping
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    // 단일 상품 조회
    @GetMapping("/{id}")
    public Product getProduct(@PathVariable Long id) {
        return productService.getProduct(id);
    }
}
