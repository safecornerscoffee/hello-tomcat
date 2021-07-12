package com.safecornerscoffee.medium.exception;

public class InvalidUsernameOrPassword extends RuntimeException {
    public InvalidUsernameOrPassword() {
        super("invalid username or password.");
    }
}
