package com.safecornerscoffee.mapper;

import com.safecornerscoffee.domain.Article;
import com.safecornerscoffee.domain.Profile;
import com.safecornerscoffee.domain.Tag;
import com.safecornerscoffee.domain.User;
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
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/web/WEB-INF/applicationContext.xml")
public class ArticleMapperTest {

    @Autowired
    ArticleMapper articleMapper;
    Article article;
    Tag tag;

    @Autowired
    UserMapper userMapper;
    User author;

    @Before
    public void beforeEach() {

        Long userId = userMapper.nextId();
        String username = "mocha";
        String email = "mocha@safecornerscoffee.com";
        String password = "mocha";
        String name = "mocha";
        String image = "mocha.png";
        Profile profile = new Profile(name, image);

        author = new User.UserBuilder(userId, username, email, password).profile(profile).build();
        userMapper.insertUser(author);

        Long articleId = articleMapper.nextId();
        String title = "cafe mocha recipe";
        String body = "try it with";
        List<Tag> tags = new ArrayList<>(Arrays.asList(
                new Tag(articleMapper.nextTagId(), "Recipe"),
                new Tag(articleMapper.nextTagId(), "Caffeine"),
                new Tag(articleMapper.nextTagId(), "Mocha")
        ));
        article = new Article.ArticleBuilder(articleId, title, body, userId).tags(tags).build();
        articleMapper.insertArticle(article);

        String tagName = "Peppermint";
        tag = new Tag(articleMapper.nextTagId(), tagName);
        articleMapper.insertTag(tag);
    }

    @After
    public void afterEach() {
        userMapper.deleteUser(author);
        articleMapper.deleteArticle(article);
        articleMapper.deleteTag(tag);
    }

    @Test
    public void nextArticleIdTest() {
        Long nextArticleId = articleMapper.nextId();
        assertThat(nextArticleId).isNotNull();
    }

    @Test
    public void selectArticleById() {

        Article savedArticle = articleMapper.selectArticleById(article.getId());

        assertThat(savedArticle.getId()).isEqualTo(article.getId());

    }

    @Test
    public void updateArticleTest() {

        String updateTitle = "mocha";
        String updateBody = "mocha";

        article.updateTitle("mocha");
        article.updateBody("mocha");

        articleMapper.updateArticle(article);

        Article updatedArticle = articleMapper.selectArticleById(article.getId());

        assertThat(updatedArticle.getId()).isEqualTo(article.getId());
        assertThat(updatedArticle.getTitle()).isEqualTo(updateTitle);
        assertThat(updatedArticle.getBody()).isEqualTo(updateBody);
    }

    @Test
    public void nextTagIdTest() {
        Long nextTagId = articleMapper.nextTagId();
        assertThat(nextTagId).isNotNull();
    }

    @Test
    @Transactional
    public void insertTagTest() {
        String tagName = "Test";
        Tag tag = new Tag(articleMapper.nextTagId(), tagName);
        articleMapper.insertTag(tag);
    }

    @Test
    public void selectTagByIdTest() {
        Tag savedTag = articleMapper.selectTagById(tag.getId());

        assertThat(savedTag.getId()).isNotNull();
        assertThat(savedTag.getId()).isEqualTo(tag.getId());
    }

    @Test
    public void selectTagByNameTest() {
        Tag savedTag = articleMapper.selectTagByName(tag.getName());

        assertThat(savedTag.getId()).isNotNull();
        assertThat(savedTag.getName()).isEqualTo(tag.getName());
    }

}