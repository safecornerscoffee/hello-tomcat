package com.safecornerscoffee.dto;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ArticleCommandTest {

    @Test
    public void builderTest() {
        ArticleCommand article = new ArticleCommand.ArticleCommandBuilder()
                .id(1L)
                .title("hello")
                .body("hello")
                .userId(2L)
                .build();

        assertEquals(1L, (long) article.getId());
        assertEquals("hello", article.getTitle());
        assertEquals("hello", article.getBody());
        assertEquals(2L, (long) article.getUserId());
    }
}