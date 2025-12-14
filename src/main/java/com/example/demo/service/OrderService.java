package com.example.demo.service;

import com.example.demo.dto.OrderRequest;
import com.example.demo.dto.OrderResponse;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public interface OrderService {

        OrderResponse placeOrder(OrderRequest request);

        OrderResponse getOrderById(Long id);

        List<OrderResponse> getOrdersForCurrentUser();

}