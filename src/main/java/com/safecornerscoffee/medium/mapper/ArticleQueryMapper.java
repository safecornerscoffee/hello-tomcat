package com.safecornerscoffee.medium.mapper;

import com.safecornerscoffee.medium.dto.ArticleResponse;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ArticleQueryMapper {
    List<ArticleResponse> getAllArticles();

    List<ArticleResponse> getArticlesByUserId(Long userId);

    List<ArticleResponse> getArticlesByTag(String tagName);

    ArticleResponse getArticleById(Long articleId);
}
