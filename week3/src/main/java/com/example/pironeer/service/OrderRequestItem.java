package com.example.pironeer.service;

public record OrderRequestItem(
        Long productId,
        int amount
){}
