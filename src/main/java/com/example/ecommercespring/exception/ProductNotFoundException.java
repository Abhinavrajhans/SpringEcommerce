package com.example.ecommercespring.exception;

public class ProductNotFoundException extends RuntimeException{
    //we extend RuntimeException as it is a Unchecked Exception.
    public ProductNotFoundException(String message){
        super(message);
    }
}

//Why Extend RuntimeException ?
// Marks it an unchecked exception . Benefit of it is Callers are not forced to
// declare or catch it .