package com.example.pironeer.service;

import com.example.pironeer.domain.Product;
import com.example.pironeer.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    // 상품 등록
    public Product register(String name, int price, int stockQuantity) {
        Product product = new Product(name, price, stockQuantity);
        return productRepository.save(product);
    }

    // 전체 상품 조회
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    // 특정 상품 조회
    public Product getProduct(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("상품을 찾을 수 없습니다."));
    }

    public Long createProduct(Product product) {
        return productRepository.save(product).getId();
    }

    public void decreaseStock(Long productId, int amount) {
        Product product = getProduct(productId);
        product.removeAmount(amount);
    }

}
