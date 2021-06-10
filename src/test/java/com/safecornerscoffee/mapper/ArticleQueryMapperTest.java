package com.safecornerscoffee.mapper;

import com.safecornerscoffee.domain.Article;
import com.safecornerscoffee.domain.Profile;
import com.safecornerscoffee.domain.Tag;
import com.safecornerscoffee.domain.User;
import com.safecornerscoffee.dto.ArticleResponse;
import com.safecornerscoffee.factory.ArticleFactory;
import com.safecornerscoffee.factory.TagFactory;
import com.safecornerscoffee.repository.ArticleRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/web/WEB-INF/applicationContext.xml")
@Transactional
public class ArticleQueryMapperTest {

    @Autowired
    ArticleRepository articleRepository;
    @Autowired
    ArticleFactory articleFactory;
    @Autowired
    TagFactory tagFactory;

    List<Article> articles;

    @Autowired
    UserMapper userMapper;
    User user, otherUser;


    @Autowired
    ArticleQueryMapper articleQueryMapper;

    @Before
    public void beforeEach() {

        Long userId = userMapper.nextId();
        String username = "mocha";
        String email = "mocha@safecornerscoffee.com";
        String password = "mocha";
        String name = "mocha";
        String image = "mocha.png";
        Profile profile = new Profile(name, image);

        user = new User.UserBuilder(userId, username, email, password).profile(profile).build();
        userMapper.insertUser(user);

        Long otherUserId = userMapper.nextId();
        String otherUsername = "cappuccino";
        String otherUserEmail = "cappuccino@safecornerscoffee.com";
        String otherUserPassword = "cappuccino";
        String otherUserName = "cappuccino";
        String otherUserImage = "cappuccino.png";
        Profile otherUserProfile = new Profile(otherUserName, otherUserImage);

        otherUser = new User.UserBuilder(otherUserId, otherUsername, otherUserEmail, otherUserPassword).profile(otherUserProfile).build();
        userMapper.insertUser(otherUser);

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
    }

    @After
    public void afterEach() {
        userMapper.deleteUser(user);
        userMapper.deleteUser(otherUser);
        for (Article article : articles) {
            articleRepository.removeArticle(article);
        }
    }


    @Test
    public void getAllArticles() {
        // todo
        List<ArticleResponse> allArticles = articleQueryMapper.getAllArticles();

        assertThat(allArticles).hasSize(articles.size());
    }

    @Test
    public void getArticleById() {
        Article article = articles.get(0);

        ArticleResponse savedArticle = articleQueryMapper.getArticleById(article.getId());

        assertThat(savedArticle.getId()).isEqualTo(article.getId());
        assertThat(savedArticle.getTitle()).isEqualTo(article.getTitle());
        assertThat(savedArticle.getBody()).isEqualTo(article.getBody());
        assertThat(savedArticle.getTags()).isEqualTo(article.getTags());
        assertThat(savedArticle.getProfileName()).isEqualTo(user.getProfile().getName());
        assertThat(savedArticle.getProfileImage()).isEqualTo(user.getProfile().getImage());
    }

    @Test
    public void getArticlesByUserId() {
        List<Article> subjectArticles = articles.stream().filter(article -> article.getUserId().equals(user.getId())).collect(Collectors.toList());

        List<ArticleResponse> foundArticles = articleQueryMapper.getArticlesByUserId(user.getId());

        assertThat(foundArticles).hasSize(subjectArticles.size());

        for (ArticleResponse foundOne : foundArticles) {
            assertThat(foundOne.getUserId()).isEqualTo(user.getId());
            assertThat(foundOne.getProfileName()).isEqualTo(user.getProfile().getName());
            assertThat(foundOne.getProfileImage()).isEqualTo(user.getProfile().getImage());
        }
    }

    @Test
    public void getArticleByTag() {
        String tagName = "Cappuccino";
        Tag tag = tagFactory.forName(tagName);
        List<Article> subjectArticles = articles.stream().filter(article -> article.getTags().contains(tag)).collect(Collectors.toList());

        List<ArticleResponse> foundArticles = articleQueryMapper.getArticlesByTag(tagName);

        assertThat(foundArticles).hasSize(subjectArticles.size());

        for (ArticleResponse foundOne : foundArticles) {
            assertThat(foundOne.getTags()).contains(tag);
        }
    }
}