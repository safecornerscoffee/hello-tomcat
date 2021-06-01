package com.safecornerscoffee.mapper;

import com.safecornerscoffee.domain.Article;
import com.safecornerscoffee.domain.Comment;
import com.safecornerscoffee.domain.Tag;
import com.safecornerscoffee.domain.User;
import com.safecornerscoffee.factory.ArticleFactory;
import com.safecornerscoffee.factory.TagFactory;
import com.safecornerscoffee.repository.ArticleRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
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
public class CommentMapperTest {

    @Autowired
    CommentMapper commentMapper;

    @Autowired
    ArticleRepository articleRepository;
    @Autowired
    ArticleFactory articleFactory;
    @Autowired
    TagFactory tagFactory;

    @Autowired
    UserMapper userMapper;

    User author, commenter;
    Article article;

    @Before
    public void beforeEach() {
        author = new User();
        author.setId(userMapper.nextId());
        author.setUsername("bluebottle");
        author.setName("bluebottle");
        author.setEmail("bluebottle");
        author.setPassword("bluebottle");
        userMapper.insertUser(author);

        commenter = new User();
        commenter.setId(userMapper.nextId());
        commenter.setUsername("bottletop");
        commenter.setName("bottletop");
        commenter.setEmail("bottletop");
        commenter.setPassword("bottletop");
        userMapper.insertUser(commenter);

        String title = "pouring coffee";
        String body = "pouring coffee";
        Long authorId = author.getId();
        List<Tag> tags = new ArrayList<>(Arrays.asList(
                tagFactory.forName("cl"),
                tagFactory.forName("go"),
                tagFactory.forName("pc")
        ));
        article = articleFactory.getArticle(title, body, authorId, tags);
        articleRepository.saveArticle(article);
    }

    @After
    public void afterEach() {
        userMapper.deleteUser(author);
        userMapper.deleteUser(commenter);
        articleRepository.removeArticle(article);
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