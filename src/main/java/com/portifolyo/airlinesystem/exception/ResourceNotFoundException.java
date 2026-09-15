package com.portifolyo.airlinesystem.exception;

/**
 * 404 Response
 */
public class ResourceNotFoundException extends RuntimeException{

    public ResourceNotFoundException(String message) {
        super(message);
    }
}
