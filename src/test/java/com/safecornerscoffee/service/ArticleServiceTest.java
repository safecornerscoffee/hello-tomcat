package com.safecornerscoffee.service;

import com.safecornerscoffee.assembler.ArticleAssembler;
import com.safecornerscoffee.factory.TagFactory;
import com.safecornerscoffee.mapper.ArticleMapper;
import com.safecornerscoffee.mapper.UserMapper;
import com.safecornerscoffee.domain.Article;
import com.safecornerscoffee.service.dto.ArticleDTO;
import com.safecornerscoffee.service.dto.UserDTO;
import io.jsonwebtoken.lang.Collections;
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

import static org.junit.Assert.*;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/web/WEB-INF/applicationContext.xml")
@Transactional
public class ArticleServiceTest {


    @Autowired
    ArticleService articleService;
    @Autowired
    ArticleMapper articleMapper;
    Article article;
    @Autowired
    TagFactory tagFactory;

    @Autowired
    UserService userService;
    @Autowired
    UserMapper userMapper;
    UserDTO author;

    @Before
    public void beforeEach() {
        author = userService.signUp("author", "author@example.com", "author", "author");

        article = new Article(articleMapper.nextId(), "articleService", "articleService", author.getId());
        articleMapper.insertArticle(article);
    }

    @After
    public void afterEach() {
        userService.dropUser(author);
        articleMapper.deleteArticle(article);
    }

    @Test
    public void readArticle() {

        ArticleDTO expected = ArticleAssembler.writeDTO(article);
        ArticleDTO actual = articleService.readArticle(article.getId());

        assertEquals(expected.getId(), actual.getId());
    }

    @Test
    public void writeArticle() {
        ArticleDTO articleDTO = new ArticleDTO.Builder()
                .title("writing test")
                .body("writing test")
                .authorId(author.getId())
                .tags(Arrays.asList(
                        tagFactory.forName("cl"),
                        tagFactory.forName("go")
                ))
                .build();

        ArticleDTO newArticleDTO = articleService.writeArticle(articleDTO);

        assertNotNull(newArticleDTO.getId());
        assertEquals(articleDTO.getTitle(), newArticleDTO.getTitle());
        assertEquals(articleDTO.getAuthorId(), newArticleDTO.getAuthorId());
    }

    @Test
    public void modifyArticle() {
        ArticleDTO editingArticle = ArticleAssembler.writeDTO(article);
        editingArticle.setTitle("i'm edited!!!");
        editingArticle.setBody("i'm also");

        ArticleDTO result = articleService.modifyArticle(editingArticle);

        assertEquals(editingArticle.getTitle(), result.getTitle());
        assertEquals(editingArticle.getBody(), result.getBody());
    }
}