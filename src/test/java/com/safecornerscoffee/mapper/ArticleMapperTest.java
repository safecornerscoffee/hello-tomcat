package com.safecornerscoffee.mapper;

import com.safecornerscoffee.domain.Article;
import com.safecornerscoffee.domain.ArticleTagRelation;
import com.safecornerscoffee.domain.Tag;
import com.safecornerscoffee.domain.User;
import com.safecornerscoffee.factory.TagFactory;
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
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/web/WEB-INF/applicationContext.xml")
@Transactional
public class ArticleMapperTest {

    private static final Logger log = LoggerFactory.getLogger(ArticleMapperTest.class);

    @Autowired
    ArticleMapper articleMapper;
    @Autowired
    TagFactory tagFactory;

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

    @Test
    public void InsertArticle() {
        Long articleId = articleMapper.nextId();
        String title = "pouring coffee";
        String body = "pouring coffee";
        Long authorId = author.getId();
        List<Tag> tags = new ArrayList<>(Arrays.asList(
                tagFactory.forName("cl"),
                tagFactory.forName("go"),
                tagFactory.forName("pc")
        ));
        Article article = new Article(articleId, title, body, authorId, tags);

        articleMapper.insertArticle(article);
        for (Tag tag : article.getTags()) {
            articleMapper.insertTag(tag);
        }
    }

    @Test
    public void selectArticleById() {
        Long articleId = articleMapper.nextId();
        String title = "pouring coffee";
        String body = "pouring coffee";
        Long authorId = author.getId();
        List<Tag> tags = new ArrayList<>(Arrays.asList(
                tagFactory.forName("cl"),
                tagFactory.forName("go"),
                tagFactory.forName("pc")
        ));
        Article article = new Article(articleId, title, body, authorId, tags);

        articleMapper.insertArticle(article);
        for (Tag tag : article.getTags()) {
            articleMapper.insertTag(tag);
            ArticleTagRelation relation = new ArticleTagRelation(article.getId(), tag.getId());
            articleMapper.insertArticleTagRelation(relation);
        }

        Article selectedArticle = articleMapper.selectArticleById(article.getId());

        assertEquals(article.getId(), selectedArticle.getId());

        assertEquals(article.getTags().size(), selectedArticle.getTags().size());
    }

    @Test
    public void updateArticleTest() {
        Long articleId = articleMapper.nextId();
        String title = "pouring coffee";
        String body = "pouring coffee";
        Long authorId = author.getId();
        List<Tag> tags = new ArrayList<>(Arrays.asList(
                tagFactory.forName("cl"),
                tagFactory.forName("go"),
                tagFactory.forName("pc")
        ));
        Article article = new Article(articleId, title, body, authorId, tags);

        articleMapper.insertArticle(article);
        for (Tag tag : article.getTags()) {
            articleMapper.insertTag(tag);
        }

        String updateTitle = "mocha";
        String updateBody = "mocha";

        article.updateTitle("mocha");
        article.updateBody("mocha");

        articleMapper.updateArticle(article);

        Article updatedArticle = articleMapper.selectArticleById(article.getId());

        assertEquals(updateTitle, updatedArticle.getTitle());
        assertEquals(updateBody, updatedArticle.getBody());
    }
}