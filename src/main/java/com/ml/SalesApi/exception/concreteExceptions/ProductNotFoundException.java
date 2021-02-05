package com.ml.SalesApi.exception.concreteExceptions;

public class ProductNotFoundException extends RuntimeException {
    public ProductNotFoundException(String errorMessage, Throwable err) {
        super(errorMessage, err);
    }
}
