package com.safecornerscoffee.service;

import com.safecornerscoffee.dao.ArticleDao;
import com.safecornerscoffee.domain.Article;
import com.safecornerscoffee.service.dto.ArticleDTO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class ArticleService {

    private ArticleDao articleDao;

    public ArticleService(ArticleDao articleDao) {
        this.articleDao = articleDao;
    }
    public ArticleDTO readArticle(Long articleId) {
        Article article = articleDao.selectArticleById(articleId);

        return ArticleDTO.from(article);
    }

    public ArticleDTO writeArticle(ArticleDTO articleDTO) {
        Long articleId = articleDao.nextId();
        Article article = new Article(articleId,
                articleDTO.getTitle(), articleDTO.getBody(), articleDTO.getAuthorId());

        articleDao.insertArticle(article);

        return ArticleDTO.from(article);
    }

    public ArticleDTO modifyArticle(ArticleDTO articleDTO) {
        if(articleDTO.getTitle() == null || articleDTO.getTitle().equals("")) {
            throw new IllegalStateException("invalid request");
        }

        Article article = articleDao.selectArticleById(articleDTO.getId());

        article.updateTitle(articleDTO.getTitle());
        article.updateBody(articleDTO.getBody());

        articleDao.updateArticle(article);

        return ArticleDTO.from(article);
    }


}
