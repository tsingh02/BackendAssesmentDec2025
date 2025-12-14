package com.example.demo.service;

import com.example.demo.dto.PageResponse;
import com.example.demo.dto.ProductRequest;
import com.example.demo.dto.ProductResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductService {
    ProductResponse createProduct(ProductRequest request);

    ProductResponse getProductById(Long id);

    PageResponse<ProductResponse> searchProducts(String name, Double minPrice, Double maxPrice, Pageable pageable);

    ProductResponse updateProduct(Long id, ProductRequest request);

    void deleteProduct(Long id);
}
