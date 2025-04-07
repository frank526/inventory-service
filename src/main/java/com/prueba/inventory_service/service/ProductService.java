package com.prueba.inventory_service.service;

import com.prueba.common_library.dto.ProductDto;
import com.prueba.inventory_service.dto.CreateProductRequest;
import com.prueba.inventory_service.dto.UpdateProductRequest;

public interface ProductService {

    
    ProductDto create(CreateProductRequest request);

    ProductDto getProduct(Long id);

    void remove(Long id);

    ProductDto update(Long id, UpdateProductRequest request);
    
}
