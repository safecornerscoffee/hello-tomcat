package com.safecornerscoffee.medium.exception;

public class NotFoundArticleException extends RuntimeException {
    public NotFoundArticleException() {
        super("NotFoundArticleException");
    }
}
