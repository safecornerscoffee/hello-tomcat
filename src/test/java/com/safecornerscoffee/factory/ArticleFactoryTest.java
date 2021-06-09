package com.safecornerscoffee.factory;

import com.safecornerscoffee.domain.Article;
import com.safecornerscoffee.domain.Tag;
import com.safecornerscoffee.domain.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/web/WEB-INF/applicationContext.xml")
public class ArticleFactoryTest {

    @Autowired
    ArticleFactory articleFactory;

    @Test
    public void createArticleTest() {
        User user = new User.UserBuilder(1L, "coffee", "coffee@safecornerscoffee.com", "coffee").build();
        String title = "pouring coffee";
        String body = "pouring coffee";
        Long userId = user.getId();
        List<Tag> tags = new ArrayList<>(Arrays.asList(
                new Tag(1L, "cl"),
                new Tag(2L, "dg")
        ));
        Article article = articleFactory.getArticle(title, body, userId, tags);

        assertThat(article).isNotNull();
        assertThat(article.getId()).isNotNull();
    }
}