package com.safecornerscoffee.service;

import com.safecornerscoffee.mapper.ArticleMapper;
import com.safecornerscoffee.domain.Article;
import com.safecornerscoffee.service.dto.ArticleDTO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;


@Service
@Transactional
public class ArticleService {

    private ArticleMapper articleMapper;

    public ArticleService(ArticleMapper articleMapper) {
        this.articleMapper = articleMapper;
    }

    public List<ArticleDTO> getArticles() {
        List<Article> articles = articleMapper.selectAllArticles();

        return articles.stream().map(ArticleDTO::from).collect(Collectors.toList());
    }

    public ArticleDTO readArticle(Long articleId) {
        Article article = articleMapper.selectArticleById(articleId);

        return ArticleDTO.from(article);
    }

    public ArticleDTO writeArticle(ArticleDTO articleDTO) {
        Long articleId = articleMapper.nextId();
        Article article = new Article(articleId,
                articleDTO.getTitle(), articleDTO.getBody(), articleDTO.getAuthorId());

        articleMapper.insertArticle(article);

        return ArticleDTO.from(article);
    }

    public ArticleDTO modifyArticle(ArticleDTO articleDTO) {
        if(articleDTO.getTitle() == null || articleDTO.getTitle().equals("")) {
            throw new IllegalStateException("invalid request");
        }

        Article article = articleMapper.selectArticleById(articleDTO.getId());

        article.updateTitle(articleDTO.getTitle());
        article.updateBody(articleDTO.getBody());

        articleMapper.updateArticle(article);

        return ArticleDTO.from(article);
    }


}
