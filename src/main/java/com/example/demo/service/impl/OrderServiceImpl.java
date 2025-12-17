package com.example.demo.service.impl;

import com.example.demo.dto.OrderItemRequest;
import com.example.demo.dto.OrderRequest;
import com.example.demo.dto.OrderResponse;
import com.example.demo.persistence.entity.Order;
import com.example.demo.persistence.entity.OrderItem;
import com.example.demo.persistence.entity.Product;
import com.example.demo.persistence.entity.User;
import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.mapper.OrderMapper;
import com.example.demo.persistence.repository.OrderRepository;
import com.example.demo.persistence.repository.ProductRepository;
import com.example.demo.service.OrderService;
import com.example.demo.service.UserService;
import com.example.demo.service.discount.DiscountCalculator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final UserService userService;
    private final OrderMapper orderMapper;
    @Autowired
    private DiscountCalculator discountCalculator;

    @Override
    @Transactional
    public OrderResponse placeOrder(OrderRequest request) {
        User user = userService.getCurrentUser();

        List<OrderItem> items = request.getItems().stream().map(itemRequest -> {
            Product product = productRepository.findByIdAndDeletedFalse(itemRequest.getProductId())
                    .orElseThrow(() -> new ResourceNotFoundException("Product not found: " + itemRequest.getProductId()));

            if (product.getQuantity() < itemRequest.getQuantity()) {
                throw new BadRequestException("Insufficient stock for product: " + product.getName());
            }

            // Decrease stock
            product.setQuantity(product.getQuantity() - itemRequest.getQuantity());
            productRepository.save(product);

            OrderItem orderItem = new OrderItem();
            orderItem.setProduct(product);
            orderItem.setQuantity(itemRequest.getQuantity());
            orderItem.setUnitPrice(product.getPrice());

            // Calculate discount per item
            double discount = discountCalculator.calculateDiscount(user,product.getPrice() * itemRequest.getQuantity());
            orderItem.setDiscountApplied(discount);
            orderItem.setTotalPrice(product.getPrice() * itemRequest.getQuantity() - discount);
            return orderItem;
        }).collect(Collectors.toList());

        double orderTotal = items.stream().mapToDouble(OrderItem::getTotalPrice).sum();

        Order order = new Order();
        order.setUser(user);
        order.setItems(items);
        order.setOrderTotal(BigDecimal.valueOf(orderTotal));

        order = orderRepository.save(order);

        log.info("Order placed successfully with id: {}", order.getId());
        return orderMapper.toOrderResponse(order);
    }

    @Override
    @Transactional(readOnly = true)
    public OrderResponse getOrderById(Long id) {
        User user = userService.getCurrentUser();
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found: " + id));

        // Optionally, restrict users to see only their orders
        if (!user.getRole().equals("ADMIN") && !order.getUser().getId().equals(user.getId())) {
            throw new BadRequestException("Access denied to order: " + id);
        }

        return orderMapper.toOrderResponse(order);
    }

    @Override
    @Transactional(readOnly = true)
    public List<OrderResponse> getOrdersForCurrentUser() {
        User user = userService.getCurrentUser();
        List<Order> orders;
        if (user.getRole().equals("ADMIN")) {
            orders = orderRepository.findAll();
        } else {
            orders = orderRepository.findByUserId(user.getId());
        }
        return orders.stream().map(orderMapper::toOrderResponse).collect(Collectors.toList());
    }
}
