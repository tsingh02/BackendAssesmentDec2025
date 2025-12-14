package com.example.demo.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class OrderItemResponse {

    private Long productId;
    private String productName;
    private Integer quantity;
    private Double unitPrice;
    private Double discountApplied;
    private BigDecimal totalPrice;
}
