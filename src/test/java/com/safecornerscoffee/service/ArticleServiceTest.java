package com.safecornerscoffee.service;

import com.safecornerscoffee.assembler.ArticleAssembler;
import com.safecornerscoffee.domain.Article;
import com.safecornerscoffee.domain.User;
import com.safecornerscoffee.dto.ArticleDTO;
import com.safecornerscoffee.dto.UserDTO;
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

import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;


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
    User author;
    @Before
    public void beforeEach() {
        String username = "mocha";
        String email = "mocha@safecornerscoffee.com";
        String password = "mocha";
        String name = "mocha";
        String imageUrl = "mocha.png";
        UserDTO createAuthorRequest = new UserDTO.UserDTOBuilder().username(username).email(email).password(password)
                .profileName(name).profileImage(imageUrl).build();

        author = userService.signUp(createAuthorRequest);

        article = new Article(articleMapper.nextId(), "articleService", "articleService", author.getId());
        articleMapper.insertArticle(article);
    }

    @After
    public void afterEach() {
        UserDTO dropUserRequest = new UserDTO.UserDTOBuilder().id(author.getId()).build();
        userService.dropUser(dropUserRequest);
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