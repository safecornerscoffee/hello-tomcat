package com.safecornerscoffee.medium.mapper;

import com.safecornerscoffee.medium.domain.Article;
import com.safecornerscoffee.medium.domain.ArticleTagRelation;
import com.safecornerscoffee.medium.domain.Tag;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Set;

@Mapper
public interface ArticleMapper {
    Long nextId();

    List<Article> selectAllArticles();

    List<Article> selectArticlesByUserId(Long userId);

    List<Article> selectArticlesByTagName(String tagName);

    Article selectArticleById(Long articleId);

    void insertArticle(Article article);

    void updateArticle(Article article);

    void deleteArticle(Article article);

    Long nextTagId();

    Tag selectTagById(Long tagId);

    Tag selectTagByName(String name);
    void insertTag(Tag tag);
    void deleteTag(Tag tag);

    Set<Tag> selectTagsByArticleId(Long articleId);

    void insertArticleTagRelation(ArticleTagRelation articleTagRelation);
    void deleteArticleTagRelation(ArticleTagRelation articleTagRelation);

}
