package com.safecornerscoffee.integration.controller;

import com.safecornerscoffee.medium.assembler.UserAssembler;
import com.safecornerscoffee.medium.domain.Article;
import com.safecornerscoffee.medium.domain.User;
import com.safecornerscoffee.medium.dto.ArticleCommand;
import com.safecornerscoffee.medium.dto.ArticleResponse;
import com.safecornerscoffee.medium.dto.UserDTO;
import com.safecornerscoffee.medium.factory.ArticleFactory;
import com.safecornerscoffee.medium.factory.TagFactory;
import com.safecornerscoffee.medium.repository.ArticleRepository;
import com.safecornerscoffee.medium.service.UserService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import static com.safecornerscoffee.medium.dto.ArticleResponseBuilder.ArticleResponseBuilder;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"file:src/main/web/WEB-INF/applicationContext.xml", "file:src/main/web/WEB-INF/dispatcher-servlet.xml"})
@WebAppConfiguration
public class ArticleControllerIntTest {

    MockMvc mockMvc;

    @Autowired
    WebApplicationContext webApplicationContext;

    @Autowired
    UserService userService;
    User user, otherUser;
    @Autowired
    ArticleRepository articleRepository;
    @Autowired
    ArticleFactory articleFactory;
    @Autowired
    TagFactory tagFactory;
    List<Article> articles;
    List<ArticleResponse> articleResponses;

    @Before
    public void beforeEach() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        setup();
    }

    @After
    public void afterEach() {
        teardown();
    }

    @Test
    public void getArticles() throws Exception {
        mockMvc.perform(get("/articles"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("articles"))
                .andExpect(model().attribute("articles", hasSize(articles.size())))
                .andExpect(model().attribute("articles", articleResponses));
    }

    @Test
    public void getArticlePageByArticleId() throws Exception {
        ArticleResponse article = articleResponses.get(0);

        mockMvc.perform(get("/articles/" + article.getId()))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("article"))
                .andExpect(model().attribute("article", article));
    }

    @Test
    @Transactional
    public void createArticle() throws Exception {

        ArticleCommand articleCommand = new ArticleCommand.ArticleCommandBuilder()
                .title("wet cappuccino recipe")
                .body("wet cappuccino recipe:")
                .userId(user.getId())
                .tags(new HashSet<>(Arrays.asList(tagFactory.forName("Recipe"),
                        tagFactory.forName("Cappuccino"),
                        tagFactory.forName("Wet Cappuccino")
                )))
                .build();

        MockHttpServletRequestBuilder request = post("/articles")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE)
                .param("title", "wet cappuccino recipe")
                .param("body", "wet cappuccino recipe:")
                .param("userId", user.getId().toString());

        mockMvc.perform(request)
                .andDo(print())
                .andExpect(status().is2xxSuccessful());

    }

    private void setup() {
        String username = "mocha";
        String email = "mocha@safecornerscoffee.com";
        String password = "mocha";
        String name = "mocha";
        String image = "mocha.png";

        String otherUsername = "cappuccino";
        String otherUserEmail = "cappuccino@safecornerscoffee.com";
        String otherUserPassword = "cappuccino";
        String otherUserName = "cappuccino";
        String otherUserImage = "cappuccino.png";

        UserDTO userSignUpCommand = new UserDTO.UserDTOBuilder().username(username).email(email).password(password).profileName(name).profileImage(image).build();
        UserDTO otherUserSignUpCommand = new UserDTO.UserDTOBuilder().username(otherUsername).email(otherUserEmail).password(otherUserPassword).profileName(otherUserName).profileImage(otherUserImage).build();
        user = userService.signUp(userSignUpCommand);
        otherUser = userService.signUp(otherUserSignUpCommand);

        articles = new ArrayList<>();
        articles.add(
                articleFactory.getArticle("mocha recipe", "mocha recipe:", user.getId(),
                        new HashSet<>(Arrays.asList(
                                tagFactory.forName("Recipe"),
                                tagFactory.forName("Mocha")))));
        articles.add(
                articleFactory.getArticle("cafe latte recipe", "cafe latte recipe:", user.getId(),
                        new HashSet<>(Arrays.asList(
                                tagFactory.forName("Recipe"),
                                tagFactory.forName("Cafe Latte")))));
        articles.add(
                articleFactory.getArticle("espresso recipe", "espresso recipe:", user.getId(),
                        new HashSet<>(Arrays.asList(
                                tagFactory.forName("Recipe"),
                                tagFactory.forName("Espresso")))));

        articles.add(
                articleFactory.getArticle("cappuccino recipe", "cappuccino recipe:", otherUser.getId(),
                        new HashSet<>(Arrays.asList(
                                tagFactory.forName("Recipe"),
                                tagFactory.forName("Cappuccino")))));
        articles.add(
                articleFactory.getArticle("wet cappuccino recipe", "wet cappuccino recipe:", otherUser.getId(),
                        new HashSet<>(Arrays.asList(
                                tagFactory.forName("Recipe"),
                                tagFactory.forName("Cappuccino"),
                                tagFactory.forName("Wet Cappuccino")))));

        for (Article article : articles) {
            articleRepository.saveArticle(article);

        }
        articleResponses = new ArrayList<>();
        articleResponses.add(ArticleResponseBuilder(articles.get(0), user).build());
        articleResponses.add(ArticleResponseBuilder(articles.get(1), user).build());
        articleResponses.add(ArticleResponseBuilder(articles.get(2), user).build());
        articleResponses.add(ArticleResponseBuilder(articles.get(3), otherUser).build());
        articleResponses.add(ArticleResponseBuilder(articles.get(4), otherUser).build());

    }

    private void teardown() {
        userService.dropUser(UserAssembler.writeDTO(user));
        userService.dropUser(UserAssembler.writeDTO(otherUser));
        for (Article article : articles) {
            articleRepository.removeArticle(article);
        }
        articleResponses.clear();
    }
}
