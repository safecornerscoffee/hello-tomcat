package com.safecornerscoffee.repository;

import com.safecornerscoffee.domain.Article;
import com.safecornerscoffee.domain.ArticleTagRelation;
import com.safecornerscoffee.domain.Tag;
import com.safecornerscoffee.mapper.ArticleMapper;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;
import java.util.Set;

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
        insertArticle(article);
        insertTags(article);
    }

    private void insertArticle(Article article) {
        articleMapper.insertArticle(article);
    }

    private void insertTags(Article article) {
        for (Tag tag : article.getTags()) {
            ArticleTagRelation relation = new ArticleTagRelation(article.getId(), tag.getId());
            articleMapper.insertArticleTagRelation(relation);
        }
    }

    public void removeArticle(Article article) {
        deleteTags(article);
        deleteArticle(article);
    }

    private void deleteArticle(Article article) {
        articleMapper.deleteArticle(article);
    }

    private void deleteTags(Article article) {
        Set<Tag> tags = articleMapper.selectTagsByArticleId(article.getId());
        for (Tag tag : tags) {
            ArticleTagRelation relation = new ArticleTagRelation(article.getId(), tag.getId());
            articleMapper.deleteArticleTagRelation(relation);
        }
    }


    public void updateArticle(Article article) {
        articleMapper.updateArticle(article);
        deleteTags(article);
        insertTags(article);
    }

    public List<Article> findArticles() {
        // todo
        return Collections.emptyList();
    }

    public Article findArticleById(Long articleId) {
        return articleMapper.selectArticleById(articleId);
    }

    public List<Article> findArticlesByUserId(Long userId) {
        return articleMapper.selectArticlesByUserId(userId);
    }

    public List<Article> findArticlesByTag(Tag tag) {
        return articleMapper.selectArticlesByTagName(tag.getName());
    }

    public List<Article> findArticlesByTags(Set<Tag> tags) {
        // todo
        return Collections.emptyList();
    }

    public Tag getTagByName(String name) {
        Tag savedTag = articleMapper.selectTagByName(name);
        if (savedTag != null) {
            return savedTag;
        }

        Long id = articleMapper.nextTagId();
        Tag newTag = new Tag(id, name);
        articleMapper.insertTag(newTag);

        return newTag;
    }


}
