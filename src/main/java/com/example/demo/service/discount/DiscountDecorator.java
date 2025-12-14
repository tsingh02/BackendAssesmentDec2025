package com.example.demo.service.discount;

import com.example.demo.persistence.entity.User;

import java.math.BigDecimal;

public abstract class DiscountDecorator implements DiscountCalculator {

    protected final DiscountCalculator delegate;

    protected DiscountDecorator(DiscountCalculator delegate) {
        this.delegate = delegate;
    }

    @Override
    public abstract double calculateDiscount(User user, double orderTotal);
}
