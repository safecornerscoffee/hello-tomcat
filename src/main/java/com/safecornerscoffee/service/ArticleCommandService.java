package com.safecornerscoffee.service;

import com.safecornerscoffee.assembler.ArticleAssembler;
import com.safecornerscoffee.domain.Article;
import com.safecornerscoffee.dto.ArticleCommand;
import com.safecornerscoffee.repository.ArticleRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class ArticleCommandService {

    private final ArticleRepository articleRepository;

    public ArticleCommandService(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    public ArticleCommand createArticle(ArticleCommand articleCommand) {
        articleCommand.setId(articleRepository.nextId());
        Article article = ArticleAssembler.createArticle(articleCommand);

        articleRepository.saveArticle(article);
        return ArticleAssembler.writeCommand(article);
    }

    public ArticleCommand updateArticle(ArticleCommand ArticleUpdateCommand) {
        if (ArticleUpdateCommand.getTitle() == null || ArticleUpdateCommand.getTitle().equals("")) {
            throw new IllegalStateException("invalid request");
        }

        Article article = articleRepository.findArticleById(ArticleUpdateCommand.getId());

        updateArticleContent(article, ArticleUpdateCommand);

        articleRepository.updateArticle(article);

        return ArticleAssembler.writeCommand(article);
    }

    private void updateArticleContent(Article article, ArticleCommand ArticleUpdateCommand) {
        article.updateTitle(ArticleUpdateCommand.getTitle());
        article.updateBody(ArticleUpdateCommand.getBody());
        article.updateTags(ArticleUpdateCommand.getTags());
    }

    public ArticleCommand deleteArticle(ArticleCommand ArticleDeleteCommand) {
        Article article = articleRepository.findArticleById(ArticleDeleteCommand.getId());
        articleRepository.removeArticle(article);
        return ArticleAssembler.writeCommand(article);
    }
}
