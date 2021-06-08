package com.safecornerscoffee.exception;

public class UserAlreadyExistException extends RuntimeException {
    public UserAlreadyExistException() {
        super("user already exist");
    }
}
