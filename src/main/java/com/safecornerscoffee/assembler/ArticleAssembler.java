package com.safecornerscoffee.assembler;

import com.safecornerscoffee.domain.Article;
import com.safecornerscoffee.dto.ArticleDTO;

public class ArticleAssembler {

    public static ArticleDTO writeDTO(Article article) {
        return new ArticleDTO.Builder()
                .id(article.getId())
                .title(article.getTitle())
                .body(article.getBody())
                .authorId(article.getAuthorId())
                .tags(article.getTags())
                .build();
    }
    public static Article createArticle(ArticleDTO articleDTO) {
        return new Article(
                articleDTO.getId(),
                articleDTO.getTitle(),
                articleDTO.getBody(),
                articleDTO.getAuthorId(),
                articleDTO.getTags()
        );
    }

}
