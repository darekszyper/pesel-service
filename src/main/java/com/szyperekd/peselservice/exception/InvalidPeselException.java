package com.szyperekd.peselservice.exception;

public class InvalidPeselException extends RuntimeException {

    public InvalidPeselException(String message) {
        super(message);
    }
}
