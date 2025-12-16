package io.github.taryckgsantos.libraryapi.exceptions;

import java.util.UUID;

public class ResourceNotFoundException extends RuntimeException{

    private static final long serialVersionUID = 1L;

    public ResourceNotFoundException(Object id) {
        super("Resource not found. Id " + id);
    }

    public ResourceNotFoundException(String message) {
        super(message);
    }
}
