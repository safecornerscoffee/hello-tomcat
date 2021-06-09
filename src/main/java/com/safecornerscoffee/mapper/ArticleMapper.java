package com.safecornerscoffee.mapper;

import com.safecornerscoffee.domain.Article;
import com.safecornerscoffee.domain.ArticleTagRelation;
import com.safecornerscoffee.domain.Tag;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ArticleMapper {
    Long nextId();

    List<Article> selectAllArticles();

    List<Article> selectArticlesByUserId(Long userId);

    Article selectArticleById(Long articleId);

    void insertArticle(Article article);

    void updateArticle(Article article);

    void deleteArticle(Article article);

    Long nextTagId();

    Tag selectTagById(Long tagId);

    Tag selectTagByName(String name);

    void insertTag(Tag tag);

    void deleteTag(Tag tag);

    List<Tag> selectTagsByArticleId(Long articleId);

    void insertArticleTagRelation(ArticleTagRelation articleTagRelation);

    void deleteArticleTagRelation(ArticleTagRelation articleTagRelation);

}
