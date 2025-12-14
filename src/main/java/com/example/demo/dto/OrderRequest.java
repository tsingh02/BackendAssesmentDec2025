package com.example.demo.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.util.List;

@Data
public class OrderRequest {

    @Valid
    @NotEmpty(message = "Order must have at least one item")
    private List<OrderItemRequest> items;
}
