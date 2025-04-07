package com.prueba.inventory_service.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prueba.common_library.dto.ProductDto;
import com.prueba.common_library.entity.Product;
import com.prueba.inventory_service.dto.CreateProductRequest;
import com.prueba.inventory_service.exception.ProductValidationException;
import com.prueba.inventory_service.repository.ProductRepository;
import com.prueba.inventory_service.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {


    @Autowired
    ProductRepository repo;

    @Override
    public ProductDto create(CreateProductRequest request) {

        if (repo.existsByCode(request.getCodigo())) {
            throw new ProductValidationException("Ya existe un producto con este codigo");
        }
        Product producto = mapToProduct(request);
        Product productCreated = repo.save(producto);
        ProductDto productDto = mapToProductDto(productCreated);
        return productDto;

    }

    private Product mapToProduct(CreateProductRequest productRequest) {

        Product producto = Product.builder()
                .name(productRequest.getNombre())
                .code(productRequest.getCodigo())
                .description(productRequest.getDescripcion())
                .price(productRequest.getPrecio())
                .stock(productRequest.getStock()).build();

        return producto;
    }

    private ProductDto mapToProductDto(Product productCreated) {

        ProductDto productoDto = ProductDto.builder()
                .id(productCreated.getId())
                .nombre(productCreated.getName())
                .codigo(productCreated.getCode())
                .description(productCreated.getDescription())
                .precio(productCreated.getPrice()).build();

        return productoDto;
    }
    
}
