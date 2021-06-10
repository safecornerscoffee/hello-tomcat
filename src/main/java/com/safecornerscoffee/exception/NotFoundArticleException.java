package com.safecornerscoffee.exception;

public class NotFoundArticleException extends RuntimeException {
    public NotFoundArticleException() {
        super("NotFoundArticleException");
    }
}
