package com.safecornerscoffee.dao;

import com.safecornerscoffee.domain.Article;

import java.util.List;


public interface ArticleDao {
    public Long nextId();
    public List<Article> selectAllArticles();
    public List<Article> selectArticlesByAuthorId(Long authorId);
    public Article selectArticleDetailsById(Long articleId);
    public Article selectArticleById(Long articleId);
    public void insertArticle(Article article);
    public void updateArticle(Article article);
    public void deleteArticle(Article article);
}
