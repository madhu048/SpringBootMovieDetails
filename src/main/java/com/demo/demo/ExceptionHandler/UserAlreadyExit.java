package com.demo.demo.ExceptionHandler;

public class UserAlreadyExit extends RuntimeException {
    public UserAlreadyExit(String message) {
        super(message);
    }
}
