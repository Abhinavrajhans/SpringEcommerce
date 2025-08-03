package com.example.ecommercespring.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleProductNotFound(ProductNotFoundException e) {
        ErrorResponse err=new ErrorResponse(
                HttpStatus.NOT_FOUND.value(),
                        e.getMessage(),
                        LocalDateTime.now()
                );
        return new ResponseEntity<>(err, HttpStatus.NOT_FOUND);
    }
    //what is there is a generic Exception
    @ExceptionHandler(Exception.class) //Every Exception comes under Exception.class
    public ResponseEntity<ErrorResponse> handleGenericException(Exception ex)
    {
        ErrorResponse err=new ErrorResponse(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "An Unexpected Error Occurred. We are Working on it...",
                LocalDateTime.now()
        );
        return new ResponseEntity<>(err, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
