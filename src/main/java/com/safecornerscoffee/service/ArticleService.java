package com.safecornerscoffee.service;

import com.safecornerscoffee.assembler.ArticleAssembler;
import com.safecornerscoffee.domain.Article;
import com.safecornerscoffee.factory.ArticleFactory;
import com.safecornerscoffee.repository.ArticleRepository;
import com.safecornerscoffee.service.dto.ArticleDTO;
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
        Long articleId = articleRepository.nextId();
        Article article = new Article(articleId,
                articleDTO.getTitle(), articleDTO.getBody(), articleDTO.getAuthorId(), articleDTO.getTags());

        articleRepository.saveArticle(article);

        return ArticleAssembler.writeDTO(article);
    }

    public ArticleDTO modifyArticle(ArticleDTO articleDTO) {
        if(articleDTO.getTitle() == null || articleDTO.getTitle().equals("")) {
            throw new IllegalStateException("invalid request");
        }

        Article article = articleRepository.findArticleById(articleDTO.getId());

        article.updateTitle(articleDTO.getTitle());
        article.updateBody(articleDTO.getBody());

        articleRepository.updateArticle(article);

        return ArticleAssembler.writeDTO(article);
    }

    public void deleteArticle(ArticleDTO articleDTO) {
        Article article = articleRepository.findArticleById(articleDTO.getId());
        articleRepository.removeArticle(article);
    }
}
