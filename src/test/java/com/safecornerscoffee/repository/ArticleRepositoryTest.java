package com.safecornerscoffee.repository;

import com.safecornerscoffee.domain.Article;
import com.safecornerscoffee.domain.Tag;
import com.safecornerscoffee.domain.User;
import com.safecornerscoffee.factory.TagFactory;
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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/web/WEB-INF/applicationContext.xml")
@Transactional
public class ArticleRepositoryTest {

    @Autowired
    ArticleRepository articleRepository;
    @Autowired
    ArticleMapper articleMapper;
    @Autowired
    TagFactory tagFactory;

    Article article;

    @Autowired
    UserMapper userMapper;
    User author;

    @Before
    public void beforeEach() {
        author = new User();
        author.setId(userMapper.nextId());
        author.setUsername("bluebottle");
        author.setName("bluebottle");
        author.setEmail("bluebottle");
        author.setPassword("bluebottle");
        userMapper.insertUser(author);

        Long articleId = articleMapper.nextId();
        String title = "pouring coffee";
        String body = "pouring coffee";
        Long authorId = author.getId();
        List<Tag> tags = new ArrayList<>(Arrays.asList(
                new Tag(articleMapper.nextTagId(), "cl"),
                new Tag(articleMapper.nextTagId(),  "go"),
                new Tag(articleMapper.nextTagId(), "pc")
        ));
        article = new Article(articleId, title, body, authorId, tags);
    }

    @After
    public void afterEach() {
        userMapper.deleteUser(author);
    }

    @Test
    public void saveArticleTest() {
        articleRepository.saveArticle(article);

        Article savedArticle = articleMapper.selectArticleById(this.article.getId());

        assertEquals(article.getId(), savedArticle.getId());
        assertEquals(article.getTags().size(), savedArticle.getTags().size());

        for (Tag tag: article.getTags()) {
            assertTrue(savedArticle.getTags().contains(tag));
        }
    }

    @Test
    public void updateArticleTest() {
        articleRepository.saveArticle(article);

        String updateTitle = "mocha";
        String updateBody = "mocha";
        article.updateTitle(updateTitle);
        article.updateBody(updateBody);
        articleRepository.updateArticle(article);

        Article updatedArticle = articleMapper.selectArticleById(this.article.getId());

        assertEquals(updateTitle, updatedArticle.getTitle());
        assertEquals(updateBody, updatedArticle.getBody());

        articleMapper.deleteArticle(this.article);
    }

    @Test
    public void removeArticleTagTest() {
        articleRepository.saveArticle(article);
        Tag tag = tagFactory.forName("cl");

        article.removeTag(tag);
        articleRepository.updateArticle(article);

        Article updatedArticle = articleMapper.selectArticleById(article.getId());

        assertFalse(updatedArticle.getTags().contains(tag));
    }
    @Test
    public void addArticleTagTest() {
        articleRepository.saveArticle(article);
        Tag tag = tagFactory.forName("dg");

        article.addTag(tag);
        articleRepository.updateArticle(article);

        Article updatedArticle = articleMapper.selectArticleById(article.getId());

        assertTrue(updatedArticle.getTags().contains(tag));
    }

    @Test
    public void ReturningEmptyListWhenArticleNotHavingTags() {
        article.setTags(Collections.emptyList());
        articleRepository.saveArticle(article);

        articleRepository.findArticleById(article.getId());

        assertNotNull(article.getTags());
        assertTrue(article.getTags().isEmpty());
    }

    @Test
    public void findArticleById() {
        articleRepository.saveArticle(article);

        Article foundOne = articleRepository.findArticleById(article.getId());

        assertEquals(article.getId(), foundOne.getId() );
    }

    @Test
    public void findArticlesByAuthorId() {
        articleRepository.saveArticle(article);

        List<Article> foundAny = articleRepository.findArticlesByAuthorId(article.getAuthorId());

        assertFalse(foundAny.isEmpty());
        for (Article a : foundAny) {
            assertEquals(article.getAuthorId(), a.getAuthorId());
        }
    }

    @Test
    public void findArticlesByTag() {
        articleRepository.saveArticle(article);
        Tag tag = tagFactory.forName("cl");
        List<Article> articlesByTag = articleRepository.findArticlesByTag(tag);

        for (Article a : articlesByTag) {
            assertTrue(a.getTags().contains(tag));
        }
    }

    @Test
    public void findArticlesByTags() {
        // todo
    }

}