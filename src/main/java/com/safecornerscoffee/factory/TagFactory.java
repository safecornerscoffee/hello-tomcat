package com.safecornerscoffee.factory;

import com.safecornerscoffee.domain.Tag;
import com.safecornerscoffee.mapper.ArticleMapper;
import org.springframework.stereotype.Component;

@Component
public class TagFactory {

    ArticleMapper articleMapper;

    public TagFactory(ArticleMapper articleMapper) {
        this.articleMapper = articleMapper;
    }

    public Tag forNameWithArticleId(Long ArticleId, String name) {
        Long id = articleMapper.nextTagId();
        return new Tag(id, ArticleId, name);
    }
    public Tag forName(String name) {
        Long id = articleMapper.nextTagId();
        return new Tag(id, name);
    }
}
