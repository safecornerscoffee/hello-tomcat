package com.safecornerscoffee.repository;

import com.safecornerscoffee.domain.Article;
import com.safecornerscoffee.domain.Profile;
import com.safecornerscoffee.domain.Tag;
import com.safecornerscoffee.domain.User;
import com.safecornerscoffee.mapper.ArticleMapper;
import com.safecornerscoffee.mapper.UserMapper;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/web/WEB-INF/applicationContext.xml")
@Transactional
public class ArticleRepositoryTest {

    @Autowired
    ArticleRepository articleRepository;
    @Autowired
    ArticleMapper articleMapper;
    Article article;

    @Autowired
    UserMapper userMapper;
    User user;

    @Before
    public void beforeEach() {
        Long userId = userMapper.nextId();
        String username = "cappuccino";
        String email = "cappuccino@safecornerscoffee.com";
        String password = "cappuccino";
        String name = "cappuccino";
        String image = "cappuccino.png";
        Profile profile = new Profile(name, image);

        user = new User.UserBuilder(userId, username, email, password).profile(profile).build();
        userMapper.insertUser(user);

        Long articleId = articleMapper.nextId();
        String title = "cappuccino recipe";
        String body = "try it with";
        Set<Tag> tags = new HashSet<>(Arrays.asList(
                articleRepository.getTagByName("Recipe"),
                articleRepository.getTagByName("Caffeine"),
                articleRepository.getTagByName("Cappuccino")
        ));
        article = new Article.ArticleBuilder(articleId, title, body, userId).tags(tags).build();
        articleRepository.saveArticle(article);
    }

    @After
    public void afterEach() {
        userMapper.deleteUser(user);
        articleRepository.removeArticle(article);
    }

    @Test
    public void saveArticleTest() {

        Article savedArticle = articleMapper.selectArticleById(article.getId());

        assertThat(savedArticle.getId()).isEqualTo(article.getId());
        assertThat(savedArticle.getTags().size()).isEqualTo(article.getTags().size());

        for (Tag tag : savedArticle.getTags()) {
            assertThat(tag).isIn(article.getTags());
        }
    }

    @Test
    public void removeArticleTest() {
        Long articleId = articleMapper.nextId();
        String title = "cappuccino recipe";
        String body = "try it with";
        Set<Tag> tags = new HashSet<>(Arrays.asList(
                articleRepository.getTagByName("Recipe"),
                articleRepository.getTagByName("Caffeine"),
                articleRepository.getTagByName("Cappuccino")
        ));
        Article newArticle = new Article.ArticleBuilder(articleId, title, body, user.getId()).tags(tags).build();
        articleRepository.saveArticle(newArticle);

        articleRepository.removeArticle(newArticle);

        Article removedArticle = articleRepository.findArticleById(newArticle.getId());
        Set<Tag> removedTags = articleMapper.selectTagsByArticleId(newArticle.getId());

        assertThat(removedArticle).isNull();
        assertThat(removedTags).isEmpty();
    }

    @Test
    public void updateArticleTest() {

        String updateTitle = "wet cappuccino recipe";
        String updateBody = "try it with";
        article.updateTitle(updateTitle);
        article.updateBody(updateBody);
        articleRepository.updateArticle(article);

        Article updatedArticle = articleMapper.selectArticleById(article.getId());

        assertThat(updatedArticle.getId()).isEqualTo(article.getId());
        assertThat(updatedArticle.getTitle()).isEqualTo(updateTitle);
        assertThat(updatedArticle.getBody()).isEqualTo(updateBody);
    }

    @Test
    public void findArticlesTest() {
        // todo
    }

    @Test
    public void findArticleByIdTest() {

        Article foundOne = articleRepository.findArticleById(article.getId());

        assertThat(foundOne).isNotNull();
        assertThat(foundOne.getId()).isEqualTo(article.getId());
    }

    @Test
    public void findArticlesByUserIdTest() {

        List<Article> foundAny = articleRepository.findArticlesByUserId(article.getUserId());

        assertThat(foundAny).isNotEmpty();
        for (Article foundOne : foundAny) {
            assertThat(foundOne.getUserId()).isEqualTo(article.getUserId());
        }
    }

    @Test
    public void findArticlesByTagTest() {
        Tag tag = articleRepository.getTagByName("Cappuccino");
        List<Article> articlesByTag = articleRepository.findArticlesByTag(tag);

        assertThat(articlesByTag).isNotEmpty();
        for (Article article : articlesByTag) {
            assertThat(tag).isIn(article.getTags());
        }
    }

    @Test
    public void findArticlesByTags() {
        // todo
    }

    @Test
    public void removeArticleTagTest() {

        Tag tag = articleRepository.getTagByName("Caffeine");

        article.removeTag(tag);
        articleRepository.updateArticle(article);

        Article updatedArticle = articleMapper.selectArticleById(article.getId());

        assertThat(tag).isNotIn(updatedArticle.getTags());
    }

    @Test
    public void addArticleTagTest() {

        Tag tag = articleRepository.getTagByName("HomeBrew");

        article.addTag(tag);
        articleRepository.updateArticle(article);

        Article updatedArticle = articleMapper.selectArticleById(article.getId());

        assertTrue(updatedArticle.getTags().contains(tag));
    }

    @Test
    public void ArticleWithoutTags() {
        Long articleId = articleMapper.nextId();
        String title = "cappuccino recipe";
        String body = "try it with";
        Set<Tag> tags = Collections.emptySet();

        Article articleWithoutTags = new Article.ArticleBuilder(articleId, title, body, user.getId()).tags(tags).build();
        articleRepository.saveArticle(articleWithoutTags);

        Article savedArticle = articleRepository.findArticleById(articleWithoutTags.getId());

        assertThat(savedArticle.getId()).isEqualTo(articleWithoutTags.getId());
        assertThat(savedArticle.getTags()).isEmpty();
    }

}