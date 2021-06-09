package com.safecornerscoffee.controller;

import com.safecornerscoffee.dto.ArticleCommand;
import com.safecornerscoffee.service.ArticleCommandService;
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

    private final ArticleCommandService articleCommandService;

    public ArticleController(ArticleCommandService articleCommandService) {
        this.articleCommandService = articleCommandService;
    }

    @GetMapping("/articles")
    public String getArticles(Model model) {
        List<ArticleCommand> articles = articleCommandService.getArticles();
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
        articleCommandService.writeArticle(article);

        return "index";
    }

    @GetMapping("/articles/{articleId}")
    public String getArticle() {
        return "articles/item";
    }

}
