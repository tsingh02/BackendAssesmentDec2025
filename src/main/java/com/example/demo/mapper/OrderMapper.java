package com.example.demo.mapper;

import com.example.demo.dto.OrderItemResponse;
import com.example.demo.dto.OrderResponse;
import com.example.demo.persistence.entity.Order;
import com.example.demo.persistence.entity.OrderItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.math.BigDecimal;
import java.util.List;

@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface OrderMapper {

    @Mapping(source = "user.id", target = "userId")
    OrderResponse toOrderResponse(Order order);

    List<OrderResponse> toOrderResponseList(List<Order> orders);

    OrderItemResponse toOrderItemResponse(OrderItem item);

    List<OrderItemResponse> toOrderItemResponseList(List<OrderItem> items);

    // BigDecimal → Double conversion
    default Double map(BigDecimal value) {
        return value != null ? value.doubleValue() : null;
    }
}
