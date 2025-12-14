package com.example.demo.service.discount;

import com.example.demo.persistence.entity.User;
import org.springframework.stereotype.Component;


public class OrderAmountDiscountDecorator extends DiscountDecorator {

    public OrderAmountDiscountDecorator(DiscountCalculator delegate) {
        super(delegate);
    }

    @Override
    public double calculateDiscount(User user, double orderTotal) {
        double discount = delegate.calculateDiscount(user, orderTotal);

        if (orderTotal > 500) {
            discount += orderTotal * 0.05; // Additional 5% discount for orders > $500
        }

        return discount;
    }
}
