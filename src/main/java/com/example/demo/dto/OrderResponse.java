package com.example.demo.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class OrderResponse {

    private Long id;
    private Long userId;
    private List<OrderItemResponse> items;
    private Double orderTotal;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
