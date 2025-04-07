package com.prueba.inventory_service.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prueba.common_library.dto.ProductDto;
import com.prueba.common_library.entity.Product;
import com.prueba.inventory_service.dto.CreateProductRequest;
import com.prueba.inventory_service.dto.UpdateProductRequest;
import com.prueba.inventory_service.exception.ProductValidationException;
import com.prueba.inventory_service.repository.ProductRepository;
import com.prueba.inventory_service.service.ProductService;

import jakarta.persistence.EntityNotFoundException;

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

    @Override
    public ProductDto getProduct(Long id) {

        Optional<Product> productFound = repo.findById(id);

        if (!productFound.isPresent()) {
            throw new EntityNotFoundException("No se encontro un producto con el id "+id);
        }

        Product product = productFound.get();
        ProductDto productDto = mapToProductDto(product);
        return productDto;

    }

    @Override
    public void remove(Long id) {
        if(!repo.existsById(id)){
            throw new EntityNotFoundException("No se encontro un producto con el id "+id);
        }
        repo.deleteById(id);
    }

    @Override
    public ProductDto update(Long id, UpdateProductRequest request) {

        Product product = repo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Producto no encontrado con id: " + id));

        product.setName(request.getNombre());
        product.setDescription(request.getDescripcion());
        product.setPrice(request.getPrecio());
        product.setStock(request.getStock());
        product.setCode(request.getCodigo());

         Product productUpdated = repo.save(product);

        ProductDto productDto = mapToProductDto(productUpdated);

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
                .stock(productCreated.getStock())
                .precio(productCreated.getPrice()).build();

        return productoDto;
    }

}
