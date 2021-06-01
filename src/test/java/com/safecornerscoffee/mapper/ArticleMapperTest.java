package com.safecornerscoffee.mapper;

import com.safecornerscoffee.domain.Article;
import com.safecornerscoffee.domain.Tag;
import com.safecornerscoffee.domain.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/web/WEB-INF/applicationContext.xml")
public class ArticleMapperTest {

    private static final Logger log = LoggerFactory.getLogger(ArticleMapperTest.class);
    @Autowired
    ArticleMapper articleMapper;

    @Autowired
    UserMapper userMapper;
    User author, otherAuthor;

    @Before
    public void beforeEach() {

        author = new User();
        author.setId(userMapper.nextId());
        author.setUsername("bluebottle");
        author.setName("bluebottle");
        author.setEmail("bluebottle");
        author.setPassword("bluebottle");
        userMapper.insertUser(author);

        otherAuthor = new User();
        otherAuthor.setId(userMapper.nextId());
        otherAuthor.setUsername("ediya");
        otherAuthor.setName("ediya");
        otherAuthor.setEmail("ediya");
        otherAuthor.setPassword("ediya");
        userMapper.insertUser(otherAuthor);
    }

    @After
    public void afterEach() {
        userMapper.deleteUser(author);
        userMapper.deleteUser(otherAuthor);
    }

    @Test
    public void nextIdTest() {
        Long nextArticleId = articleMapper.nextId();
        assertNotNull(nextArticleId);
    }

    @Test(expected = DataIntegrityViolationException.class)
    public void ThrowErrorWhenInsertArticleWithNotPresentUserId() {
        Long articleId = articleMapper.nextId();
        String title = "test title";
        String body = "this is a test";
        Article article = new Article(articleId, title, body, -1L);
        articleMapper.insertArticle(article);
    }

    @Test
    public void InsertArticle() {
        Long articleId = articleMapper.nextId();
        String title = "hello world";
        String body = "hello world";
        Article article = new Article(articleId, title, body, author.getId());
        articleMapper.insertArticle(article);
    }
    @Test
    public void SelectArticleById() {
        Long articleId = articleMapper.nextId();
        String title = "test title";
        String body = "this is a test";
        Article article = new Article(articleId, title, body, author.getId());
        articleMapper.insertArticle(article);

        Article selectedArticle = articleMapper.selectArticleById(articleId);

        assertEquals(article.getId(), selectedArticle.getId());
        assertEquals(article.getAuthorId(), selectedArticle.getAuthorId());
        assertEquals(article.getTitle(), selectedArticle.getTitle());

    }

    @Test
    public void selectArticleDetailsById() {
        Article article = articleMapper.selectArticleDetailsById(author.getId());

        assertEquals(article.getTags().size(), 2);
        for (Tag tag : article.getTags()) {
            log.debug(tag.toString());
            log.debug(tag.toString());
        }
    }

    @Test
    public void selectAllArticles() {
        String title = "test title";
        String body = "this is a test";
        Article firstArticle = new Article(articleMapper.nextId(), title, body, author.getId());
        Article secondArticle = new Article(articleMapper.nextId(), title, body, author.getId());
        articleMapper.insertArticle(firstArticle);
        articleMapper.insertArticle(secondArticle);
        List<Article> articles = articleMapper.selectAllArticles();

        assertNotNull(articles);
    }

    @Test
    public void selectArticleByAuthorId() {
        String title = "test title";
        String body = "this is a test";
        Article firstArticle = new Article(articleMapper.nextId(), title, body, author.getId());
        Article secondArticle = new Article(articleMapper.nextId(), title, body, author.getId());
        Article AnotherArticle = new Article(articleMapper.nextId(), title, body, otherAuthor.getId());

        articleMapper.insertArticle(firstArticle);
        articleMapper.insertArticle(secondArticle);
        articleMapper.insertArticle(AnotherArticle);

        List<Article> articles = articleMapper.selectArticlesByAuthorId(author.getId());

        assertEquals(articles.size(), 2);

        for(Article article : articles) {
            assertEquals(article.getAuthorId(), author.getId());
        }

    }
}