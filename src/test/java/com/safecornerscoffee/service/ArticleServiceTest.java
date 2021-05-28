package com.safecornerscoffee.service;

import com.safecornerscoffee.dao.ArticleDao;
import com.safecornerscoffee.dao.UserDao;
import com.safecornerscoffee.domain.Article;
import com.safecornerscoffee.domain.User;
import com.safecornerscoffee.service.dto.ArticleDTO;
import com.safecornerscoffee.service.dto.UserDTO;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.*;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/web/WEB-INF/applicationContext.xml")
@Transactional
public class ArticleServiceTest {


    @Autowired
    ArticleService articleService;
    @Autowired
    ArticleDao articleDao;
    Article article;

    @Autowired
    UserService userService;
    @Autowired
    UserDao userDao;
    UserDTO author;

    @Before
    public void beforeEach() {
        author = userService.signUp("author@example.com", "author", "author");

        article = new Article(articleDao.nextId(), "articleService", "articleService", author.getId());
        articleDao.insertArticle(article);
    }

    @After
    public void afterEach() {
        userService.dropUser(author);
        articleDao.deleteArticle(article);
    }

    @Test
    public void readArticle() {

        ArticleDTO expected = ArticleDTO.from(article);
        ArticleDTO actual = articleService.readArticle(article.getId());

        assertEquals(expected, actual);
    }

    @Test
    public void writeArticle() {
        ArticleDTO articleDTO = new ArticleDTO.Builder()
                .title("writing test")
                .body("writing test")
                .authorId(author.getId())
                .build();

        ArticleDTO newArticleDTO = articleService.writeArticle(articleDTO);

        assertNotNull(newArticleDTO.getId());
        assertEquals(articleDTO.getTitle(), newArticleDTO.getTitle());
        assertEquals(articleDTO.getAuthorId(), newArticleDTO.getAuthorId());
    }

    @Test
    public void modifyArticle() {
        ArticleDTO editingArticle = ArticleDTO.from(article);
        editingArticle.setTitle("i'm edited!!!");
        editingArticle.setBody("i'm also");

        ArticleDTO result = articleService.modifyArticle(editingArticle);

        assertEquals(editingArticle.getTitle(), result.getTitle());
        assertEquals(editingArticle.getBody(), result.getBody());
    }
}