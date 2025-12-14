package com.example.demo.config;

import com.example.demo.service.discount.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DiscountConfig {

    @Bean
    public DiscountCalculator discountCalculator(BaseDiscountCalculator base) {
        return new OrderAmountDiscountDecorator(
                new PremiumUserDiscountDecorator(base)
        );
    }
}
