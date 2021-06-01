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

    void saveArticle(Article article) {
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

    void removeArticle(Article article) {
        deleteTags(article);
        articleMapper.deleteArticle(article);
    }

    void updateArticle(Article article) {
        articleMapper.updateArticle(article);
        deleteTags(article);
        insertTags(article);
    }

    private void deleteTags(Article article) {
        List<Tag> tags = articleMapper.selectTagsByArticleId(article.getId());
        for (Tag tag : tags) {
            ArticleTagRelation relation = new ArticleTagRelation(article.getId(), tag.getId());
            articleMapper.deleteArticleTagRelation(relation);
            articleMapper.deleteTag(tag);
        }
    }


    Article findArticleById(Long articleId) {
        return articleMapper.selectArticleById(articleId);
    }

    List<Article> findArticles() {
        return Collections.emptyList();
    }

    List<Article> findArticlesByAuthorId(Long authorId) {
        return articleMapper.selectArticlesByAuthorId(authorId);
    }

    List<Article> findArticlesByTag(Tag tag) {
        return Collections.emptyList();
    }

}
