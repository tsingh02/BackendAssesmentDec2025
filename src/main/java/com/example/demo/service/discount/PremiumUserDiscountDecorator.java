package com.example.demo.service.discount;

import com.example.demo.enums.Role;
import com.example.demo.persistence.entity.User;
import org.springframework.stereotype.Component;


public class PremiumUserDiscountDecorator extends DiscountDecorator {

    public PremiumUserDiscountDecorator(DiscountCalculator delegate) {
        super(delegate);
    }

    @Override
    public double calculateDiscount(User user, double orderTotal) {
        double discount = delegate.calculateDiscount(user, orderTotal);

        if (Role.PREMIUM_USER.toString().equalsIgnoreCase(user.getRole().toString())) {
            discount += orderTotal * 0.10; // 10% discount for premium users
        }

        return discount;
    }
}
