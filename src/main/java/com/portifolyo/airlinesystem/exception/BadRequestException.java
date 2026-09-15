package com.portifolyo.airlinesystem.exception;

/**
 * 400 Response
 */
public class BadRequestException extends RuntimeException {
    public BadRequestException(String message) {
        super(message);
    }
}
