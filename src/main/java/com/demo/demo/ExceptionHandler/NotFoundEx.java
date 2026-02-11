package com.demo.demo.ExceptionHandler;

public class NotFoundEx extends RuntimeException {

    public NotFoundEx(String message) {
        super(message);
    }
}
