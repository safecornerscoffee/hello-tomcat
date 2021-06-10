package com.safecornerscoffee.factory;

import com.safecornerscoffee.domain.Article;
import com.safecornerscoffee.domain.Tag;
import com.safecornerscoffee.mapper.ArticleMapper;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class ArticleFactory {
    private final ArticleMapper articleMapper;

    public ArticleFactory(ArticleMapper articleMapper) {
        this.articleMapper = articleMapper;
    }

    public Article getArticle(String title, String body, Long userId, Set<Tag> tags) {
        Long articleId = articleMapper.nextId();

        return new Article(articleId, title, body, userId, tags);
    }
}
