package com.safecornerscoffee.medium.exception;

public class ArticleAlreadyExistException extends RuntimeException {
    public ArticleAlreadyExistException() {
        super("article already exist");
    }
}
