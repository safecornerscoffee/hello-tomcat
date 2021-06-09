package com.safecornerscoffee.service;

import com.safecornerscoffee.assembler.ArticleAssembler;
import com.safecornerscoffee.domain.Article;
import com.safecornerscoffee.dto.ArticleCommand;
import com.safecornerscoffee.repository.ArticleRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;


@Service
@Transactional
public class ArticleService {

    private final ArticleRepository articleRepository;

    public ArticleService(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    public List<ArticleCommand> getArticles() {
        List<Article> articles = articleRepository.findArticles();

        return articles.stream().map(ArticleAssembler::writeCommand).collect(Collectors.toList());
    }

    public ArticleCommand readArticle(Long articleId) {

        Article article = articleRepository.findArticleById(articleId);
        return ArticleAssembler.writeCommand(article);
    }

    public ArticleCommand writeArticle(ArticleCommand articleCommand) {
        articleCommand.setId(articleRepository.nextId());
        Article article = ArticleAssembler.createArticle(articleCommand);

        articleRepository.saveArticle(article);

        return ArticleAssembler.writeCommand(article);
    }

    public ArticleCommand updateArticle(ArticleCommand updateArticleRequest) {
        if (updateArticleRequest.getTitle() == null || updateArticleRequest.getTitle().equals("")) {
            throw new IllegalStateException("invalid request");
        }

        Article article = articleRepository.findArticleById(updateArticleRequest.getId());

        article.updateTitle(updateArticleRequest.getTitle());
        article.updateBody(updateArticleRequest.getBody());

        articleRepository.updateArticle(article);

        return ArticleAssembler.writeCommand(article);
    }

    public void deleteArticle(ArticleCommand deleteArticleRequest) {
        Article article = articleRepository.findArticleById(deleteArticleRequest.getId());
        articleRepository.removeArticle(article);
    }
}
