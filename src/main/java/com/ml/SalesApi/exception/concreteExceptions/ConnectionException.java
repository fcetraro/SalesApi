package com.ml.SalesApi.exception.concreteExceptions;

public class ConnectionException extends RuntimeException {
    public ConnectionException(String errorMessage, Throwable err) {
        super(errorMessage, err);
    }
}
