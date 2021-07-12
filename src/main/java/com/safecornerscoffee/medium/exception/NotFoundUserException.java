package com.safecornerscoffee.medium.exception;

public class NotFoundUserException extends RuntimeException {
    public NotFoundUserException() {
        super("NotFoundUserException");
    }
}
