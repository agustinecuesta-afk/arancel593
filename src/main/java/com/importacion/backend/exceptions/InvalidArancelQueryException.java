package com.importacion.backend.exceptions;

public class InvalidArancelQueryException extends RuntimeException {

    public InvalidArancelQueryException(String message) {
        super(message);
    }
}
