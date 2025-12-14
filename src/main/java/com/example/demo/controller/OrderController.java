package com.example.demo.controller;

import com.example.demo.dto.OrderRequest;
import com.example.demo.dto.OrderResponse;
import com.example.demo.service.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
@Slf4j
@SecurityRequirement(name = "bearerAuth")
@Validated
public class OrderController {

    private final OrderService orderService;

    @Operation(summary = "Place an order", description = "Places an order for multiple products and applies discounts")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Order placed successfully"),
            @ApiResponse(responseCode = "400", description = "Insufficient stock")
    })
    @PreAuthorize("hasAnyRole('USER','PREMIUM_USER')")
    @PostMapping("/create")
    public ResponseEntity<OrderResponse> placeOrder(@RequestBody @Validated OrderRequest request) {
        log.info("Placing order for user with {} items", request.getItems().size());
        OrderResponse response = orderService.placeOrder(request);
        return ResponseEntity.status(201).body(response);
    }

    @Operation(summary = "Get order by ID", description = "Retrieve order details by order ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Order found"),
            @ApiResponse(responseCode = "404", description = "Order not found")
    })
    @PreAuthorize("hasAnyRole('USER','PREMIUM_USER')")
    @GetMapping("/{id}")
    public ResponseEntity<OrderResponse> getOrder(@PathVariable Long id) {
        log.info("Fetching order with id: {}", id);
        return ResponseEntity.ok(orderService.getOrderById(id));
    }

    @Operation(summary = "Get all orders for current user", description = "Retrieve all orders placed by the current user")
    @PreAuthorize("hasAnyRole('USER','PREMIUM_USER')")
    @GetMapping
    public ResponseEntity<List<OrderResponse>> getAllOrders() {
        log.info("Fetching all orders for current user");
        return ResponseEntity.ok(orderService.getOrdersForCurrentUser());
    }
}


