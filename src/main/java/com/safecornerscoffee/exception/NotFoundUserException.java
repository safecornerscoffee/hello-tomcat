package com.safecornerscoffee.exception;

public class NotFoundUserException extends RuntimeException {
    public NotFoundUserException() {
        super("NotFoundUserException");
    }
}
