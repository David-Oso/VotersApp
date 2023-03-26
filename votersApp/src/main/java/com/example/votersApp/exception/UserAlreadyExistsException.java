package com.example.votersApp.exception;

public class UserAlreadyExistsException extends BusinessLogicException{
    public UserAlreadyExistsException(String message) {
        super(message);
    }
}
