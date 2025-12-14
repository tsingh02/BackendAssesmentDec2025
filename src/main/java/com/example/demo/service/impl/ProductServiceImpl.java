package com.example.demo.service.impl;

import com.example.demo.dto.PageResponse;
import com.example.demo.dto.ProductRequest;
import com.example.demo.dto.ProductResponse;
import com.example.demo.persistence.entity.Product;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.mapper.ProductMapper;
import com.example.demo.persistence.entity.Product;
import com.example.demo.persistence.repository.ProductRepository;
import com.example.demo.persistence.repository.ProductRepository;
import com.example.demo.service.ProductService;
import jakarta.persistence.criteria.Predicate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    @Override
    public ProductResponse createProduct(ProductRequest request) {
        Product product = productMapper.toEntity(request);
        product.setDeleted(false);
        product = productRepository.save(product);
        log.info("Created product with id: {}", product.getId());
        return productMapper.toDto(product);
    }

    @Override
    @Transactional(readOnly = true)
    public ProductResponse getProductById(Long id) {
        Product product = productRepository.findByIdAndDeletedFalse(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id: " + id));
        return productMapper.toDto(product);
    }

    @Override
    @Transactional(readOnly = true)
    public PageResponse<ProductResponse> searchProducts(String name, Double minPrice, Double maxPrice, Pageable pageable) {
        String searchName = (name != null) ? name : "";
        double min = (minPrice != null) ? minPrice : 0.0;
        double max = (maxPrice != null) ? maxPrice : Double.MAX_VALUE;

        Page<Product> products = productRepository.findByDeletedFalseAndNameContainingIgnoreCaseAndPriceBetween(
                searchName, min, max, pageable
        );

        return PageResponse.from(products, productMapper::toDto);
    }

    @Override
    public ProductResponse updateProduct(Long id, ProductRequest request) {
        Product product = productRepository.findByIdAndDeletedFalse(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id: " + id));

        product.setName(request.getName());
        product.setDescription(request.getDescription());
        product.setPrice(request.getPrice());
        product.setQuantity(request.getQuantity());

        product = productRepository.save(product);
        log.info("Updated product with id: {}", product.getId());
        return productMapper.toDto(product);
    }

    @Override
    public void deleteProduct(Long id) {
        Product product = productRepository.findByIdAndDeletedFalse(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id: " + id));

        product.setDeleted(true);
        productRepository.save(product);
        log.info("Soft deleted product with id: {}", id);
    }
}
