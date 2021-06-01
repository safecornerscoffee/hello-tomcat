package com.safecornerscoffee.mapper;

import com.safecornerscoffee.domain.Article;
import com.safecornerscoffee.domain.Comment;
import com.safecornerscoffee.domain.User;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/web/WEB-INF/applicationContext.xml")
public class CommentMapperTest {

    @Autowired
    CommentMapper commentMapper;

    @Autowired
    ArticleMapper articleMapper;

    @Autowired
    UserMapper userMapper;

    User author, commenter;
    Article article;

    @BeforeClass
    public static void beforeClass() {

    }

    @Before
    public void beforeEach() {
        author = new User();
        author.setId(userMapper.nextId());
        author.setUsername("bluebottle");
        author.setName("bluebottle");
        author.setEmail("bluebottle");
        author.setPassword("bluebottle");

        commenter = new User();
        commenter.setId(userMapper.nextId());
        commenter.setUsername("bottletop");
        commenter.setName("bottletop");
        commenter.setEmail("bottletop");
        commenter.setPassword("bottletop");


        Long articleId = 1L;
        String title = "comment test";
        String body = "comment test";

        article = new Article(articleId, title, body, author.getId());
        articleMapper.insertArticle(article);

    }

    @After
    public void afterEach() {
        userMapper.deleteUser(author);
        userMapper.deleteUser(commenter);
        articleMapper.deleteArticle(article);
    }

    @Test
    public void insertComment() {
        Comment comment = new Comment(article.getId(), commenter.getId(), "Hello");
        commentMapper.insertComment(comment);
    }

    @Test
    public void deleteComment() {
        Comment comment = new Comment(article.getId(), commenter.getId(), "Hello");
        commentMapper.deleteComment(comment);
    }

    @Test
    public void SelectCommentById() {
        Comment comment = new Comment(article.getId(), commenter.getId(), "Hello");
        commentMapper.insertComment(comment);

        Comment selectedComment = commentMapper.SelectCommentById(comment.getId());
        assertEquals(comment.getId(), selectedComment.getId());
    }

    @Test
    public void selectCommentsByArticleId() {
        commentMapper.insertComment(new Comment(article.getId(), commenter.getId(), "Hello"));
        commentMapper.insertComment(new Comment(article.getId(), commenter.getId(), "Hello"));
        commentMapper.insertComment(new Comment(article.getId(), commenter.getId(), "Hello"));
        List<Comment> comments = commentMapper.SelectCommentsByArticleId(article.getId());

        assertEquals(comments.size(), 3);
    }

}