package com.safecornerscoffee.exception;

public class ArticleAlreadyExistException extends RuntimeException {
    public ArticleAlreadyExistException() {
        super("article already exist");
    }
}
