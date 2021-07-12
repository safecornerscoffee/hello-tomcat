package com.safecornerscoffee.medium.exception;

public class UserAlreadyExistException extends RuntimeException {
    public UserAlreadyExistException() {
        super("user already exist");
    }
}
