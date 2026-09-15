package com.portifolyo.airlinesystem.exception;

/**
 * 409 Response
 */
public class SeatNotAvaibleException extends RuntimeException{

    public SeatNotAvaibleException(String message) {
        super(message);
    }
}
