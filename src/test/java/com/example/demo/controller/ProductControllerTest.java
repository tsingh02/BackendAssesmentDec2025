package com.example.demo.controller;

import com.example.demo.dto.ProductRequest;
import com.example.demo.dto.ProductResponse;
import com.example.demo.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class ProductControllerTest {

    @Mock
    private ProductService productService;

    @InjectMocks
    private ProductController productController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateProduct() {
        ProductRequest request = new ProductRequest();
        request.setName("Sample Product");
        request.setPrice(100.0);

        ProductResponse expectedResponse = new ProductResponse();
        expectedResponse.setId(1L);
        expectedResponse.setName(request.getName());
        expectedResponse.setPrice(request.getPrice());

        when(productService.createProduct(request)).thenReturn(expectedResponse);

        ResponseEntity<ProductResponse> responseEntity  = productController.createProduct(request);
        ProductResponse actualResponse = responseEntity.getBody();
        assertEquals(expectedResponse.getId(), actualResponse.getId());
        assertEquals(expectedResponse.getName(), actualResponse.getName());
        assertEquals(expectedResponse.getPrice(), actualResponse.getPrice());

        verify(productService, times(1)).createProduct(request);
    }



    @Test
    void testGetProductById() {
        Long productId = 1L;
        ProductResponse expectedResponse = new ProductResponse();
        expectedResponse.setId(productId);
        expectedResponse.setName("Product1");
        expectedResponse.setPrice(50.0);
        when(productService.getProductById(productId)).thenReturn(expectedResponse);

        ResponseEntity<ProductResponse> responseEntity = productController.getProduct(productId);
        ProductResponse actualResponse = responseEntity.getBody();
        assertEquals(expectedResponse.getId(), actualResponse.getId());
        assertEquals(expectedResponse.getName(), actualResponse.getName());

        verify(productService, times(1)).getProductById(productId);
    }

    @Test
    void testUpdateProduct() {
        Long productId = 1L;
        ProductRequest request = new ProductRequest();
        request.setName("Updated Product");
        request.setPrice(200.0);

        ProductResponse expectedResponse = new ProductResponse();
        expectedResponse.setId(productId);
        expectedResponse.setName("Updated Product");
        expectedResponse.setPrice(200.0);
        when(productService.updateProduct(productId, request)).thenReturn(expectedResponse);

        ResponseEntity<ProductResponse> responseEntity = productController.updateProduct(productId, request);
        ProductResponse actualResponse = responseEntity.getBody();
        assertEquals(expectedResponse.getName(), actualResponse.getName());
        assertEquals(expectedResponse.getPrice(), actualResponse.getPrice());

        verify(productService, times(1)).updateProduct(productId, request);
    }

    @Test
    void testDeleteProduct() {
        Long productId = 1L;

        doNothing().when(productService).deleteProduct(productId);

        productController.deleteProduct(productId);

        verify(productService, times(1)).deleteProduct(productId);
    }
}
