package com.safecornerscoffee.medium.assembler;

import com.safecornerscoffee.medium.domain.Article;
import com.safecornerscoffee.medium.dto.ArticleCommand;

public class ArticleAssembler {

    public static ArticleCommand writeCommand(Article article) {
        return new ArticleCommand.ArticleCommandBuilder()
                .id(article.getId())
                .title(article.getTitle())
                .body(article.getBody())
                .userId(article.getUserId())
                .tags(article.getTags())
                .build();
    }

    public static Article createArticle(ArticleCommand articleCommand) {
        return new Article(
                articleCommand.getId(),
                articleCommand.getTitle(),
                articleCommand.getBody(),
                articleCommand.getUserId(),
                articleCommand.getTags()
        );
    }

}
