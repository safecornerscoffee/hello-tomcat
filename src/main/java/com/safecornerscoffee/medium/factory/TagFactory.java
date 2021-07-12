package com.safecornerscoffee.medium.factory;

import com.safecornerscoffee.medium.domain.Tag;
import com.safecornerscoffee.medium.repository.ArticleRepository;
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
