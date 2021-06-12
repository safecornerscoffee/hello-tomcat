package com.safecornerscoffee.controller;

import com.safecornerscoffee.dto.ArticleCommand;
import com.safecornerscoffee.dto.ArticleResponse;
import com.safecornerscoffee.service.ArticleCommandService;
import com.safecornerscoffee.service.ArticleQueryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

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
    public String newPage() {

        return "article/new";
    }

    @GetMapping("/articles/edit")
    public String editArticlePage() {
        return "article/edit";
    }


    @GetMapping("/articles")
    public String articleListPage(Model model) {
        List<ArticleResponse> articles = articleQueryService.getAllArticles();
        model.addAttribute("articles", articles);
        return "article/list";
    }

//    @GetMapping("/articles")
//    public String articleListPageByTag(@RequestParam(name="tag") String tagName, Model model) {
//        List<ArticleResponse> articles = articleQueryService.getArticlesByTag(tagName);
//        model.addAttribute("articles", articles);
//        return "article/list";
//    }

    @GetMapping("/articles/{articleId}")
    public String articlePage(@PathVariable Long articleId, Model model) {
        ArticleResponse article = articleQueryService.getArticleById(articleId);
        model.addAttribute("article", article);
        return "article/item";
    }


    @PostMapping("/articles/{articleId}")
    public String postArticle(ArticleCommand article) {
        articleCommandService.createArticle(article);
        return "index";
    }

}
