package com.safecornerscoffee.repository;

import com.safecornerscoffee.domain.Article;
import com.safecornerscoffee.domain.ArticleTagRelation;
import com.safecornerscoffee.domain.Tag;
import com.safecornerscoffee.mapper.ArticleMapper;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;

@Repository
public class ArticleRepository {

    private final ArticleMapper articleMapper;

    public ArticleRepository(ArticleMapper articleMapper) {
        this.articleMapper = articleMapper;
    }

    public Long nextId() {
        return articleMapper.nextId();
    }

    public void saveArticle(Article article) {
        articleMapper.insertArticle(article);
        insertTags(article);
    }

    private void insertTags(Article article) {
        for (Tag tag : article.getTags()) {
            articleMapper.insertTag(tag);
            ArticleTagRelation relation = new ArticleTagRelation(article.getId(), tag.getId());
            articleMapper.insertArticleTagRelation(relation);
        }
    }

    private void deleteTags(Article article) {
        List<Tag> tags = articleMapper.selectTagsByArticleId(article.getId());
        for (Tag tag : tags) {
            ArticleTagRelation relation = new ArticleTagRelation(article.getId(), tag.getId());
            articleMapper.deleteArticleTagRelation(relation);
            articleMapper.deleteTag(tag);
        }
    }

    public void removeArticle(Article article) {
        deleteTags(article);
        articleMapper.deleteArticle(article);
    }

    public void updateArticle(Article article) {
        articleMapper.updateArticle(article);
        deleteTags(article);
        insertTags(article);
    }

    public Article findArticleById(Long articleId) {
        return articleMapper.selectArticleById(articleId);
    }

    public List<Article> findArticles() {
        return Collections.emptyList();
    }

    public List<Article> findArticlesByUserId(Long authorId) {
        return articleMapper.selectArticlesByUserId(authorId);
    }

    public List<Article> findArticlesByTag(Tag tag) {
        return Collections.emptyList();
    }
}
