package com.safecornerscoffee.medium.service;

import com.safecornerscoffee.medium.dto.ArticleResponse;
import com.safecornerscoffee.medium.mapper.ArticleQueryMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleQueryService {

    private final ArticleQueryMapper articleQueryMapper;

    public ArticleQueryService(ArticleQueryMapper articleQueryMapper) {
        this.articleQueryMapper = articleQueryMapper;
    }

    public List<ArticleResponse> getAllArticles() {
        return articleQueryMapper.getAllArticles();
    }

    public List<ArticleResponse> getArticlesByUserId(Long userId) {
        return articleQueryMapper.getArticlesByUserId(userId);
    }

    public List<ArticleResponse> getArticlesByTag(String tagName) {
        return articleQueryMapper.getArticlesByTag(tagName);
    }

    public ArticleResponse getArticleById(Long articleId) {
        return articleQueryMapper.getArticleById(articleId);
    }
}
