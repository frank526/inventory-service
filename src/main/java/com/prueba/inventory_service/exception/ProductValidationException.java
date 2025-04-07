package com.prueba.inventory_service.exception;

public class ProductValidationException extends RuntimeException {

    public ProductValidationException(String mensaje) {
        super(mensaje);
    }
    
}
