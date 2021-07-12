package com.safecornerscoffee.integration.factory;

import com.safecornerscoffee.medium.domain.Article;
import com.safecornerscoffee.medium.domain.Tag;
import com.safecornerscoffee.medium.domain.User;
import com.safecornerscoffee.medium.factory.ArticleFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

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
        Set<Tag> tags = new HashSet<>(Arrays.asList(
                new Tag(1L, "cl"),
                new Tag(2L, "dg")
        ));
        Article article = articleFactory.getArticle(title, body, userId, tags);

        assertThat(article).isNotNull();
        assertThat(article.getId()).isNotNull();
    }
}