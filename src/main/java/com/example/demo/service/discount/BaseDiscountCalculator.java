package com.example.demo.service.discount;

import com.example.demo.persistence.entity.User;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class BaseDiscountCalculator implements DiscountCalculator {

    @Override
    public double calculateDiscount(User user, double orderTotal) {
        // Base calculator applies no discount
        return 0;
    }
}
