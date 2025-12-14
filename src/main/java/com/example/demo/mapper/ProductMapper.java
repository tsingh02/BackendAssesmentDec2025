package com.example.demo.mapper;

import com.example.demo.dto.ProductRequest;
import com.example.demo.dto.ProductResponse;
import com.example.demo.persistence.entity.Product;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    Product toEntity(ProductRequest request);

    ProductResponse toDto(Product product);
    List<ProductResponse> toDtoList(List<Product> products);

    void updateEntityFromDto(ProductRequest request, @MappingTarget Product product);
}
