package com.safecornerscoffee.controller;

import com.safecornerscoffee.dto.ArticleCommand;
import com.safecornerscoffee.dto.ArticleResponse;
import com.safecornerscoffee.dto.ErrorResponse;
import com.safecornerscoffee.exception.ArticleAlreadyExistException;
import com.safecornerscoffee.exception.NotFoundArticleException;
import com.safecornerscoffee.service.ArticleCommandService;
import com.safecornerscoffee.service.ArticleQueryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ArticleController {
    private static final Logger log = LoggerFactory.getLogger(ArticleController.class);

    private final ArticleCommandService articleCommandService;
    private final ArticleQueryService articleQueryService;

    public ArticleController(ArticleCommandService articleCommandService, ArticleQueryService articleQueryService) {
        this.articleCommandService = articleCommandService;
        this.articleQueryService = articleQueryService;
    }

    @GetMapping("/articles/new")
    public String newArticlePage() {

        return "article/new-article";
    }

    @GetMapping("/articles/edit")
    public String editArticlePage() {
        return "article/edit-article";
    }


    @GetMapping("/articles")
    public String articlesPage(Model model) {
        List<ArticleResponse> articles = articleQueryService.getAllArticles();
        model.addAttribute("articles", articles);
        return "article/articles";
    }

    @GetMapping(value = "/articles", params = "tag")
    public String articleListPageByTag(@RequestParam(name = "tag", required = true) String tagName, Model model) {
        List<ArticleResponse> articles = articleQueryService.getArticlesByTag(tagName);
        model.addAttribute("articles", articles);
        return "article/articles";
    }

    @GetMapping("/articles/{articleId}")
    public String articlePage(@PathVariable Long articleId, Model model) {
        ArticleResponse article = articleQueryService.getArticleById(articleId);
        model.addAttribute("article", article);
        return "article/article";
    }


    @PostMapping("/articles")
    @ResponseStatus(HttpStatus.CREATED)
    public String createArticle(ArticleCommand article, Model model) {
        ArticleCommand savedArticle = articleCommandService.createArticle(article);
        model.addAttribute("article", article);
        return "article/articles";
    }

    @ExceptionHandler(NotFoundArticleException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String errorPage(Exception e, Model model) {
        ErrorResponse error = new ErrorResponse(e.getMessage());
        model.addAttribute("error", error);
        return "error";
    }

    @ExceptionHandler(ArticleAlreadyExistException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleErrors(Exception e, Model model) {
        ErrorResponse error = new ErrorResponse(e.getMessage());
        model.addAttribute("error", error);
        return "error";
    }
}
