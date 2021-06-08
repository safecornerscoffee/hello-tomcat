package com.safecornerscoffee.service;

import com.safecornerscoffee.assembler.ArticleAssembler;
import com.safecornerscoffee.domain.Article;
import com.safecornerscoffee.dto.ArticleDTO;
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

    public List<ArticleDTO> getArticles() {
        List<Article> articles = articleRepository.findArticles();

        return articles.stream().map(ArticleAssembler::writeDTO).collect(Collectors.toList());
    }

    public ArticleDTO readArticle(Long articleId) {

        Article article = articleRepository.findArticleById(articleId);
        return ArticleAssembler.writeDTO(article);
    }

    public ArticleDTO writeArticle(ArticleDTO articleDTO) {
        articleDTO.setId(articleRepository.nextId());
        Article article = ArticleAssembler.createArticle(articleDTO);

        articleRepository.saveArticle(article);

        return ArticleAssembler.writeDTO(article);
    }

    public ArticleDTO updateArticle(ArticleDTO updateArticleRequest) {
        if (updateArticleRequest.getTitle() == null || updateArticleRequest.getTitle().equals("")) {
            throw new IllegalStateException("invalid request");
        }

        Article article = articleRepository.findArticleById(updateArticleRequest.getId());

        article.updateTitle(updateArticleRequest.getTitle());
        article.updateBody(updateArticleRequest.getBody());

        articleRepository.updateArticle(article);

        return ArticleAssembler.writeDTO(article);
    }

    public void deleteArticle(ArticleDTO deleteArticleRequest) {
        Article article = articleRepository.findArticleById(deleteArticleRequest.getId());
        articleRepository.removeArticle(article);
    }
}
