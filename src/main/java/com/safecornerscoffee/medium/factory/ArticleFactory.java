package com.safecornerscoffee.medium.factory;

import com.safecornerscoffee.medium.domain.Article;
import com.safecornerscoffee.medium.domain.Tag;
import com.safecornerscoffee.medium.mapper.ArticleMapper;
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
