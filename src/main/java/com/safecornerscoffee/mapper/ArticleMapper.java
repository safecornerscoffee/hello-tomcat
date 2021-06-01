package com.safecornerscoffee.mapper;

import com.safecornerscoffee.domain.Article;
import com.safecornerscoffee.domain.Tag;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ArticleMapper {
    Long nextId();
    List<Article> selectAllArticles();
    List<Article> selectArticlesByAuthorId(Long authorId);
    Article selectArticleDetailsById(Long articleId);
    Article selectArticleById(Long articleId);
    void insertArticle(Article article);
    void updateArticle(Article article);
    void deleteArticle(Article article);

    Long nextTagId();
    void insertTag(Tag tag);
    void deleteTag(Tag tag);

}
