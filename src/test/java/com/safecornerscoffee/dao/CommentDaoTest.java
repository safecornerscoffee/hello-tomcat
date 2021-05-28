package com.safecornerscoffee.dao;

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
public class CommentDaoTest {

    @Autowired
    CommentDao commentDao;

    @Autowired
    ArticleDao articleDao;

    @Autowired
    UserDao userDao;

    User author, commenter;
    Article article;

    @BeforeClass
    public static void beforeClass() {

    }

    @Before
    public void beforeEach() {
        author = new User();
        author.setName("bluebottle");
        author.setEmail("bluebottle");
        author.setPassword("bluebottle");
        Long userId = userDao.insertUser(author);
        author.setId(userId);

        commenter = new User();
        commenter.setName("commenter");
        commenter.setEmail("commenter");
        commenter.setPassword("commenter");
        Long commenterId = userDao.insertUser(commenter);
        commenter.setId(commenterId);

        Long articleId = articleDao.nextId();
        String title = "comment test";
        String body = "comment test";

        article = new Article(articleId, title, body, author.getId());
        articleDao.insertArticle(article);

    }

    @After
    public void afterEach() {
        userDao.deleteUser(author);
        userDao.deleteUser(commenter);
        articleDao.deleteArticle(article);
    }

    @Test
    public void insertComment() {
        Comment comment = new Comment(article.getId(), commenter.getId(), "Hello");
        commentDao.insertComment(comment);
    }

    @Test
    public void deleteComment() {
        Comment comment = new Comment(article.getId(), commenter.getId(), "Hello");
        commentDao.deleteComment(comment);
    }

    @Test
    public void SelectCommentById() {
        Comment comment = new Comment(article.getId(), commenter.getId(), "Hello");
        commentDao.insertComment(comment);

        Comment selectedComment = commentDao.SelectCommentById(comment.getId());
        assertEquals(comment.getId(), selectedComment.getId());
    }

    @Test
    public void selectCommentsByArticleId() {
        commentDao.insertComment(new Comment(article.getId(), commenter.getId(), "Hello"));
        commentDao.insertComment(new Comment(article.getId(), commenter.getId(), "Hello"));
        commentDao.insertComment(new Comment(article.getId(), commenter.getId(), "Hello"));
        List<Comment> comments = commentDao.SelectCommentsByArticleId(article.getId());

        assertEquals(comments.size(), 3);
    }

}