package com.prueba.inventory_service.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.prueba.common_library.dto.ProductDto;
import com.prueba.inventory_service.dto.CreateProductRequest;
import com.prueba.inventory_service.service.ProductService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/product")
public class ProductController {


    @Autowired
    ProductService productService;

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody @Valid CreateProductRequest request) {

        Map<String, Object> response = new HashMap<>();
        ProductDto producto = productService.create(request);

        response.put("success", Boolean.TRUE);
        response.put("datos", producto);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getProduct(@PathVariable String id) {

        Map<String, Object> response = new HashMap<>();
        ProductDto producto = productService.getProduct(id);

        response.put("success", Boolean.TRUE);
        response.put("datos", producto);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    
}
