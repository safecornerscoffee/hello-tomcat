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

    public Tag forName(String name) {
        Tag tag = articleMapper.selectTagByName(name);
        if(tag == null) {
            Long id = articleMapper.nextTagId();
            return new Tag(id, name);
        }
        return tag;
    }
}
