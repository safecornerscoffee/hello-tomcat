package com.safecornerscoffee.exception;

public class InvalidUsernameOrPassword extends RuntimeException {
    public InvalidUsernameOrPassword() {
        super("invalid username or password.");
    }
}
