package com.geekbrains.ExceptionHandler;

public class WareNotFoundException extends RuntimeException{
    public WareNotFoundException(String message) {
        super(message);
    }
}
