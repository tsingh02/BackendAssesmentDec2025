package com.example.demo.persistence.repository;

import com.example.demo.persistence.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface ProductRepository
        extends JpaRepository<Product, Long>, JpaSpecificationExecutor<Product> {
    Optional<Product> findByIdAndDeletedFalse(Long id);

    Page<Product> findByDeletedFalseAndNameContainingIgnoreCaseAndPriceBetween(
            String name, Double minPrice, Double maxPrice, Pageable pageable
    );

}
