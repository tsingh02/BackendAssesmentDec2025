package com.example.demo.persistence.repository;

import com.example.demo.persistence.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface  OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByUserId(Long userId);
}
