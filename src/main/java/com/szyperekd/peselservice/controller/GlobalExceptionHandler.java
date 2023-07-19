package com.szyperekd.peselservice.controller;

import com.szyperekd.peselservice.exception.InvalidPeselException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(InvalidPeselException.class)
    public ResponseEntity<?> handleInvalidPeselException(InvalidPeselException invalidPeselException) {
        String errorMessage = invalidPeselException.getMessage();
        return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
    }
}
