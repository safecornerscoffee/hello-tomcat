package com.safecornerscoffee.dao;

import com.safecornerscoffee.domain.Article;
import com.safecornerscoffee.domain.User;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/web/WEB-INF/applicationContext.xml")
public class ArticleDaoTest {

    @Autowired
    ArticleDao articleDao;

    @Autowired
    UserDao userDao;
    User author, otherAuthor;

    @Before
    public void beforeEach() {
        author = new User();
        author.setName("bluebottle");
        author.setEmail("bluebottle");
        author.setPassword("bluebottle");
        Long userId = userDao.insertUser(author);
        author.setId(userId);
        otherAuthor = new User();
        otherAuthor.setName("mega-coffee");
        otherAuthor.setEmail("mega-coffee");
        otherAuthor.setPassword("mega-coffee");
        userId = userDao.insertUser(otherAuthor);
        otherAuthor.setId(userId);
    }

    @After
    public void afterEach() {
        userDao.deleteUser(author);
        userDao.deleteUser(otherAuthor);
    }

    @Test
    public void nextIdTest() {
        Long nextArticleId = articleDao.nextId();
        assertNotNull(nextArticleId);
    }

    @Test(expected = DataIntegrityViolationException.class)
    public void ThrowErrorWhenInsertArticleWithNotPresentUserId() {
        Long articleId = articleDao.nextId();
        String title = "test title";
        String body = "this is a test";
        Article article = new Article(articleId, title, body, -1L);
        articleDao.insertArticle(article);
    }

    @Test
    public void InsertArticle() {
        Long articleId = articleDao.nextId();
        String title = "test title";
        String body = "this is a test";
        Article article = new Article(articleId, title, body, author.getId());
        articleDao.insertArticle(article);
    }
    @Test
    public void SelectArticleById() {
        Long articleId = articleDao.nextId();
        String title = "test title";
        String body = "this is a test";
        Article article = new Article(articleId, title, body, author.getId());
        articleDao.insertArticle(article);

        Article selectedArticle = articleDao.selectArticleById(articleId);

        assertEquals(article.getId(), selectedArticle.getId());
        assertEquals(article.getAuthorId(), selectedArticle.getAuthorId());
        assertEquals(article.getTitle(), selectedArticle.getTitle());

    }

    @Test
    public void selectAllArticles() {
        String title = "test title";
        String body = "this is a test";
        Article firstArticle = new Article(articleDao.nextId(), title, body, author.getId());
        Article secondArticle = new Article(articleDao.nextId(), title, body, author.getId());
        articleDao.insertArticle(firstArticle);
        articleDao.insertArticle(secondArticle);
        List<Article> articles = articleDao.selectAllArticles();

        assertEquals(articles.size(), 2);
    }

    @Test
    public void selectArticleByAuthorId() {
        String title = "test title";
        String body = "this is a test";
        Article firstArticle = new Article(articleDao.nextId(), title, body, author.getId());
        Article secondArticle = new Article(articleDao.nextId(), title, body, author.getId());
        Article AnotherArticle = new Article(articleDao.nextId(), title, body, otherAuthor.getId());

        articleDao.insertArticle(firstArticle);
        articleDao.insertArticle(secondArticle);
        articleDao.insertArticle(AnotherArticle);

        List<Article> articles = articleDao.selectArticlesByAuthorId(author.getId());

        assertEquals(articles.size(), 2);

        for(Article article : articles) {
            assertEquals(article.getAuthorId(), author.getId());
        }

    }
}