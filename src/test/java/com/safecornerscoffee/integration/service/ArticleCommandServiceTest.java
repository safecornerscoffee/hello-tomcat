package com.safecornerscoffee.integration.service;

import com.safecornerscoffee.assembler.ArticleAssembler;
import com.safecornerscoffee.domain.Article;
import com.safecornerscoffee.domain.Profile;
import com.safecornerscoffee.domain.User;
import com.safecornerscoffee.dto.ArticleCommand;
import com.safecornerscoffee.exception.NotFoundArticleException;
import com.safecornerscoffee.factory.ArticleFactory;
import com.safecornerscoffee.factory.TagFactory;
import com.safecornerscoffee.mapper.UserMapper;
import com.safecornerscoffee.repository.ArticleRepository;
import com.safecornerscoffee.service.ArticleCommandService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.HashSet;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/web/WEB-INF/applicationContext.xml")
@Transactional
public class ArticleCommandServiceTest {

    @Autowired
    ArticleCommandService articleCommandService;
    @Autowired
    ArticleRepository articleRepository;
    @Autowired
    ArticleFactory articleFactory;
    @Autowired
    TagFactory tagFactory;

    Article article;

    @Autowired
    UserMapper userMapper;
    User user;

    @Before
    public void beforeEach() {
        Long userId = userMapper.nextId();
        String username = "cappuccino";
        String email = "cappuccino@safecornerscoffee.com";
        String password = "cappuccino";
        String name = "cappuccino";
        String image = "cappuccino.png";
        Profile profile = new Profile(name, image);

        user = new User.UserBuilder(userId, username, email, password).profile(profile).build();
        userMapper.insertUser(user);

        article = articleFactory.getArticle("cappuccino recipe", "cappuccino recipe:", user.getId(),
                new HashSet<>(Arrays.asList(
                        tagFactory.forName("Recipe"),
                        tagFactory.forName("Cappuccino"))));
        articleRepository.saveArticle(article);
    }

    @After
    public void afterEach() {
        userMapper.deleteUser(user);
        articleRepository.removeArticle(article);
    }

    @Test
    public void writeArticle() {

        ArticleCommand articleCommand = new ArticleCommand.ArticleCommandBuilder()
                .title("wet cappuccino recipe")
                .body("wet cappuccino recipe:")
                .userId(user.getId())
                .tags(new HashSet<>(Arrays.asList(tagFactory.forName("Recipe"),
                        tagFactory.forName("Cappuccino"),
                        tagFactory.forName("Wet Cappuccino")
                )))
                .build();

        ArticleCommand responseArticleCommand = articleCommandService.createArticle(articleCommand);

        assertThat(responseArticleCommand.getId()).isNotNull();
        assertThat(responseArticleCommand.getTitle()).isEqualTo(articleCommand.getTitle());
        assertThat(responseArticleCommand.getBody()).isEqualTo(articleCommand.getBody());
        assertThat(responseArticleCommand.getUserId()).isEqualTo(articleCommand.getUserId());
    }

    @Test
    public void updateArticle() {
        ArticleCommand updateCommand = ArticleAssembler.writeCommand(article);
        updateCommand.setTitle("wet cappuccino recipe v2");
        updateCommand.setBody("wet cappuccino recipe v2:");

        ArticleCommand updateResponse = articleCommandService.updateArticle(updateCommand);

        assertThat(updateResponse.getId()).isEqualTo(article.getId());
        assertThat(updateResponse.getTitle()).isEqualTo(updateCommand.getTitle());
        assertThat(updateResponse.getBody()).isEqualTo(updateCommand.getBody());
    }

    @Test
    public void deleteArticle() {
        Article subjectArticle = articleFactory.getArticle("espresso recipe", "espresso recipe:", user.getId(),
                new HashSet<>(Arrays.asList(
                        tagFactory.forName("Recipe"),
                        tagFactory.forName("Espresso"))));
        articleRepository.saveArticle(subjectArticle);
        ArticleCommand deleteCommand = ArticleAssembler.writeCommand(subjectArticle);

        ArticleCommand deleteResponse = articleCommandService.deleteArticle(deleteCommand);

        assertThat(deleteResponse.getId()).isEqualTo(deleteCommand.getId());
    }

    @Test
    public void removeNoneExistArticle() {
        Article noneExistArticle = articleFactory.getArticle("espresso recipe", "espresso recipe:", user.getId(),
                new HashSet<>(Arrays.asList(
                        tagFactory.forName("Recipe"),
                        tagFactory.forName("Espresso"))));

        ArticleCommand deleteCommand = ArticleAssembler.writeCommand(noneExistArticle);

        assertThatThrownBy(() -> {
            ArticleCommand deleteResponse = articleCommandService.deleteArticle(deleteCommand);
        }).isInstanceOf(NotFoundArticleException.class)
                .hasMessageContaining("NotFoundArticleException");
    }
}