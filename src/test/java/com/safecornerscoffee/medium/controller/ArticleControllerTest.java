package com.safecornerscoffee.medium.controller;

import com.safecornerscoffee.medium.assembler.ArticleAssembler;
import com.safecornerscoffee.medium.domain.Article;
import com.safecornerscoffee.medium.domain.Profile;
import com.safecornerscoffee.medium.domain.Tag;
import com.safecornerscoffee.medium.domain.User;
import com.safecornerscoffee.medium.dto.ArticleCommand;
import com.safecornerscoffee.medium.dto.ArticleResponse;
import com.safecornerscoffee.medium.service.ArticleCommandService;
import com.safecornerscoffee.medium.service.ArticleQueryService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import static com.safecornerscoffee.medium.dto.ArticleResponseBuilder.ArticleResponseBuilder;
import static org.mockito.BDDMockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(MockitoJUnitRunner.class)
public class ArticleControllerTest {

    @Mock
    ArticleCommandService articleCommandService;
    @Mock
    ArticleQueryService articleQueryService;
    @InjectMocks
    ArticleController articleController;

    MockMvc mockMvc;
    MockHttpSession mockHttpSession;

    List<Article> articles;
    User user, otherUser;
    List<ArticleResponse> articleList;

    @Before
    public void beforeEach() {
        mockMvc = MockMvcBuilders.standaloneSetup(articleController).build();
        mockHttpSession = new MockHttpSession();

        setup();
    }

    @Test
    public void getArticleListPage() throws Exception {

        given(articleQueryService.getAllArticles()).willReturn(articleList);

        mockMvc.perform(get("/articles"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("article/list"))
                .andExpect(model().attributeExists("articles"))
                .andExpect(model().attribute("articles", articleList))
                .andReturn()
                .getModelAndView();

        verify(articleQueryService).getAllArticles();

    }

    @Test
    public void getArticlePage() throws Exception {
        ArticleResponse article = articleList.get(0);

        given(articleQueryService.getArticleById(article.getId())).willReturn(article);

        mockMvc.perform(get("/articles/" + article.getId()))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("article/item"))
                .andExpect(model().attribute("article", article));

        verify(articleQueryService).getArticleById(any(Long.class));

    }

    @Test
    public void getArticlesByTagName() throws Exception {
        String tagName = "Cappuccino";
        List<ArticleResponse> articleListByTag = new ArrayList<>();
        for (ArticleResponse articleResponse : articleList) {
            for (Tag tag : articleResponse.getTags()) {
                if (tag.getName().equals(tagName)) {
                    articleListByTag.add(articleResponse);
                    break;
                }
            }
        }

        given(articleQueryService.getArticlesByTag(any(String.class))).willReturn(articleListByTag);

        mockMvc.perform(get("/articles").param("tag", tagName))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("article/list"))
                .andExpect(model().attribute("articles", articleListByTag));

        verify(articleQueryService).getArticlesByTag(any(String.class));

    }

    @Test
    public void createArticle() throws Exception {
        ArticleCommand article = ArticleAssembler.writeCommand(articles.get(0));
        given(articleCommandService.createArticle(any(ArticleCommand.class)))
                .willReturn(article);

        mockMvc.perform(post("/articles"))
                .andExpect(status().is2xxSuccessful())
                .andExpect(view().name("article/list"))
                .andExpect(model().attributeExists("article"));

        verify(articleCommandService).createArticle((any(ArticleCommand.class)));
    }

    private void setup() {
        Long userId = 1L;
        String username = "mocha";
        String email = "mocha@safecornerscoffee.com";
        String password = "mocha";
        String name = "mocha";
        String image = "mocha.png";
        Profile profile = new Profile(name, image);

        user = new User.UserBuilder(userId, username, email, password).profile(profile).build();

        Long otherUserId = 2L;
        String otherUsername = "cappuccino";
        String otherUserEmail = "cappuccino@safecornerscoffee.com";
        String otherUserPassword = "cappuccino";
        String otherUserName = "cappuccino";
        String otherUserImage = "cappuccino.png";
        Profile otherUserProfile = new Profile(otherUserName, otherUserImage);

        otherUser = new User.UserBuilder(otherUserId, otherUsername, otherUserEmail, otherUserPassword).profile(otherUserProfile).build();

        articles = new ArrayList<>();
        articles.add(
                new Article.ArticleBuilder(1L, "mocha recipe", "mocha recipe:", 1L)
                        .tags(new HashSet<>(Arrays.asList(
                                new Tag(1L, "Recipe"),
                                new Tag(2L, "Mocha"))))
                        .build());
        articles.add(
                new Article.ArticleBuilder(2L, "cafe latte recipe", "cafe latte recipe:", 1L)
                        .tags(new HashSet<>(Arrays.asList(
                                new Tag(1L, "Recipe"),
                                new Tag(3L, "Cafe Latte"))))
                        .build());
        articles.add(
                new Article.ArticleBuilder(3L, "espresso recipe", "espresso recipe:", 1L)
                        .tags(new HashSet<>(Arrays.asList(
                                new Tag(1L, "Recipe"),
                                new Tag(3L, "Espresso"))))
                        .build());
        articles.add(
                new Article.ArticleBuilder(4L, "cappuccino recipe", "cappuccino recipe:", 2L)
                        .tags(new HashSet<>(Arrays.asList(
                                new Tag(1L, "Recipe"),
                                new Tag(4L, "Cappuccino"))))
                        .build());
        articles.add(
                new Article.ArticleBuilder(5L, "wet cappuccino recipe", "wet cappuccino recipe:", 2L)
                        .tags(new HashSet<>(Arrays.asList(
                                new Tag(1L, "Recipe"),
                                new Tag(4L, "Cappuccino"),
                                new Tag(5L, "Wet Cappuccino"))))
                        .build());

        articleList = new ArrayList<>();
        articleList.add(ArticleResponseBuilder(articles.get(0), user).build());
        articleList.add(ArticleResponseBuilder(articles.get(1), user).build());
        articleList.add(ArticleResponseBuilder(articles.get(2), user).build());
        articleList.add(ArticleResponseBuilder(articles.get(3), otherUser).build());
        articleList.add(ArticleResponseBuilder(articles.get(4), otherUser).build());
    }
}