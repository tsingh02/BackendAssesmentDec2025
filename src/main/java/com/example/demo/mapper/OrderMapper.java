package com.example.demo.mapper;

import com.example.demo.dto.OrderItemResponse;
import com.example.demo.dto.OrderResponse;
import com.example.demo.persistence.entity.Order;
import com.example.demo.persistence.entity.OrderItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface OrderMapper {

    @Mapping(source = "user.id", target = "userId")
    @Mapping(source = "items", target = "items")
    OrderResponse toDto(Order order);

    List<OrderResponse> toDtoList(List<Order> orders);

    default OrderItemResponse toOrderItemDto(OrderItem item) {
        if (item == null) return null;
        OrderItemResponse dto = new OrderItemResponse();
        dto.setProductId(item.getProduct().getId());
        dto.setProductName(item.getProduct().getName());
        dto.setQuantity(item.getQuantity());
        dto.setUnitPrice(item.getUnitPrice());
        dto.setDiscountApplied(item.getDiscountApplied());
        dto.setTotalPrice(BigDecimal.valueOf(item.getTotalPrice()));
        return dto;
    }

    default List<OrderItemResponse> mapItems(List<OrderItem> items) {
        return items.stream().map(this::toOrderItemDto).collect(Collectors.toList());
    }
}
