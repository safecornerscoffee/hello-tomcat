package com.safecornerscoffee.service.dto;

import org.junit.Test;

import static org.junit.Assert.*;

public class ArticleDTOTest {

    @Test
    public void builderTest() {
        ArticleDTO article = new ArticleDTO.Builder()
                .id(1L)
                .title("hello")
                .body("hello")
                .authorId(2L)
                .build();

        assertEquals(1L, (long) article.getId());
        assertEquals("hello", article.getTitle());
        assertEquals("hello", article.getBody());
        assertEquals(2L, (long) article.getAuthorId());
    }
}