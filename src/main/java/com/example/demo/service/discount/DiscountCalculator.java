package com.example.demo.service.discount;

import com.example.demo.persistence.entity.User;

import java.math.BigDecimal;

public interface DiscountCalculator {

    /**
     * Calculate discount for a given user and order total
     * @param user the user placing the order
     * @param orderTotal the total amount before discount
     * @return discount amount to subtract from order total
     */
    double calculateDiscount(User user, double orderTotal);
}
