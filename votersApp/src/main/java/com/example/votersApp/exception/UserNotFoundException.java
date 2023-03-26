package com.example.votersApp.exception;

public class UserNotFoundException extends BusinessLogicException{
    public UserNotFoundException(String message) {
        super(message);
    }
}
