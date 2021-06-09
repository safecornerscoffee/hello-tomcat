package com.safecornerscoffee.controller;

import com.safecornerscoffee.dto.ArticleCommand;
import com.safecornerscoffee.service.ArticleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class ArticleController {
    private static final Logger log = LoggerFactory.getLogger(ArticleController.class);

    private final ArticleService articleService;

    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @GetMapping("/articles")
    public String getArticles(Model model) {
        List<ArticleCommand> articles = articleService.getArticles();
        model.addAttribute("articles", articles);
        return "articles/list";
    }

    @GetMapping("/articles/new")
    public String createArticle() {

        return "articles/new";
    }

    @GetMapping("/articles/edit")
    public String editArticle() {
        return "articles/edit";
    }

    @PostMapping("/articles")
    public String postArticle(ArticleCommand article) {
        log.info(article.toString());
        articleService.writeArticle(article);

        return "index";
    }

    @GetMapping("/articles/{articleId}")
    public String getArticle() {
        return "articles/item";
    }

}
