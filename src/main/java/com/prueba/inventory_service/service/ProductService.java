package com.prueba.inventory_service.service;

import com.prueba.common_library.dto.ProductDto;
import com.prueba.inventory_service.dto.CreateProductRequest;

public interface ProductService {

    
    ProductDto create(CreateProductRequest request);
    
}
