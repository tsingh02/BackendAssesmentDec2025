package com.example.demo.persistence.repository;

import com.example.demo.persistence.entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
    // Mostly handled via Order entity; repository optional unless needed for custom queries
}
