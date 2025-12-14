package com.example.demo.controller;

import com.example.demo.dto.OrderItemRequest;
import com.example.demo.dto.OrderItemResponse;
import com.example.demo.dto.OrderRequest;
import com.example.demo.dto.OrderResponse;
import com.example.demo.service.OrderService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class OrderControllerTest {

    @Mock
    private OrderService orderService;

    @InjectMocks
    private OrderController orderController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testPlaceOrder() {
        OrderRequest request = new OrderRequest();
        request.setItems(Arrays.asList(new OrderItemRequest(1L,1), new OrderItemRequest(2L, 2)));

        OrderItemResponse item1 = new OrderItemResponse();
        item1.setProductName("item1");
        item1.setQuantity(1);
        item1.setUnitPrice(10.0);

        OrderItemResponse item2 = new OrderItemResponse();
        item2.setProductName("item2");
        item2.setQuantity(2);
        item2.setUnitPrice(15.0);

        OrderResponse expectedResponse = new OrderResponse();
        expectedResponse.setId(1L);
        expectedResponse.setUserId(100L);
        expectedResponse.setItems(Arrays.asList(item1, item2));
        expectedResponse.setOrderTotal(40.0);
        expectedResponse.setCreatedAt(LocalDateTime.now());
        expectedResponse.setUpdatedAt(LocalDateTime.now());

        when(orderService.placeOrder(request)).thenReturn(expectedResponse);

        ResponseEntity<OrderResponse> responseEntity = orderController.placeOrder(request);

        assertEquals(201, responseEntity.getStatusCode().value());
        assertEquals(expectedResponse.getId(), responseEntity.getBody().getId());
        assertEquals(expectedResponse.getItems().size(), responseEntity.getBody().getItems().size());
        assertEquals(expectedResponse.getOrderTotal(), responseEntity.getBody().getOrderTotal());

        verify(orderService, times(1)).placeOrder(request);
    }

    @Test
    void testGetOrder() {
        Long orderId = 1L;

        OrderItemResponse item = new OrderItemResponse();
        item.setProductName("item1");
        item.setQuantity(1);
        item.setUnitPrice(10.0);

        OrderResponse expectedResponse = new OrderResponse();
        expectedResponse.setId(orderId);
        expectedResponse.setUserId(100L);
        expectedResponse.setItems(List.of(item));
        expectedResponse.setOrderTotal(10.0);
        expectedResponse.setCreatedAt(LocalDateTime.now());
        expectedResponse.setUpdatedAt(LocalDateTime.now());

        when(orderService.getOrderById(orderId)).thenReturn(expectedResponse);

        ResponseEntity<OrderResponse> responseEntity = orderController.getOrder(orderId);

        assertEquals(200, responseEntity.getStatusCode().value());
        assertEquals(expectedResponse.getId(), responseEntity.getBody().getId());
        assertEquals(expectedResponse.getOrderTotal(), responseEntity.getBody().getOrderTotal());

        verify(orderService, times(1)).getOrderById(orderId);
    }

    @Test
    void testGetAllOrders() {
        OrderItemResponse item1 = new OrderItemResponse();
        item1.setProductName("item1");
        item1.setQuantity(1);
        item1.setUnitPrice(10.0);

        OrderItemResponse item2 = new OrderItemResponse();
        item2.setProductName("item2");
        item2.setQuantity(2);
        item2.setUnitPrice(15.0);

        OrderResponse o1 = new OrderResponse();
        o1.setId(1L);
        o1.setUserId(100L);
        o1.setItems(List.of(item1));
        o1.setOrderTotal(10.0);
        o1.setCreatedAt(LocalDateTime.now());
        o1.setUpdatedAt(LocalDateTime.now());

        OrderResponse o2 = new OrderResponse();
        o2.setId(2L);
        o2.setUserId(100L);
        o2.setItems(List.of(item2));
        o2.setOrderTotal(30.0);
        o2.setCreatedAt(LocalDateTime.now());
        o2.setUpdatedAt(LocalDateTime.now());

        List<OrderResponse> orders = Arrays.asList(o1, o2);

        when(orderService.getOrdersForCurrentUser()).thenReturn(orders);

        ResponseEntity<List<OrderResponse>> responseEntity = orderController.getAllOrders();

        assertEquals(200, responseEntity.getStatusCode().value());
        assertEquals(2, responseEntity.getBody().size());

        verify(orderService, times(1)).getOrdersForCurrentUser();
    }
}
