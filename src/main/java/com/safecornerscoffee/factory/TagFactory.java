package com.safecornerscoffee.factory;

import com.safecornerscoffee.domain.Tag;
import com.safecornerscoffee.repository.ArticleRepository;
import org.springframework.stereotype.Component;

@Component
public class TagFactory {

    ArticleRepository articleRepository;

    public TagFactory(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    public Tag forName(String name) {
        return articleRepository.getTagByName(name);
    }
}
