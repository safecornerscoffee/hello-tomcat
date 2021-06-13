package com.safecornerscoffee.service;

import com.safecornerscoffee.assembler.ArticleAssembler;
import com.safecornerscoffee.domain.Article;
import com.safecornerscoffee.domain.Tag;
import com.safecornerscoffee.dto.ArticleCommand;
import com.safecornerscoffee.repository.ArticleRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.HashSet;

import static org.mockito.BDDMockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ArticleCommandServiceTest {

    @InjectMocks
    ArticleCommandService articleCommandService;
    @Mock
    ArticleRepository articleRepository;

    @Test
    public void writeArticle() {
        ArticleCommand writeCommand = new ArticleCommand.ArticleCommandBuilder()
                .id(1L)
                .title("wet cappuccino recipe")
                .body("wet cappuccino recipe:")
                .userId(1L)
                .tags(new HashSet<>(Arrays.asList(
                        new Tag(1L, "Recipe"),
                        new Tag(2L, "Cappuccino"),
                        new Tag(3L, "Wet Cappuccino")
                )))
                .build();

        given(articleRepository.nextId()).willReturn(1L);

        ArticleCommand writtenArticle = articleCommandService.createArticle(writeCommand);

        verify(articleRepository).saveArticle(any(Article.class));

    }

    @Test
    public void updateArticle() {
        ArticleCommand articleCommand = new ArticleCommand.ArticleCommandBuilder()
                .id(1L)
                .title("cappuccino recipe")
                .body("cappuccino recipe:")
                .userId(1L)
                .tags(new HashSet<>(Arrays.asList(
                        new Tag(1L, "Recipe"),
                        new Tag(2L, "Cappuccino"),
                        new Tag(3L, "Cappuccino")
                )))
                .build();
        Article article = ArticleAssembler.createArticle(articleCommand);
        ArticleCommand updateCommand = new ArticleCommand.ArticleCommandBuilder().id(1L).title("cappuccino recipe").body("cappuccino recipe:").build();
        given(articleRepository.findArticleById(articleCommand.getId())).willReturn(article);

        articleCommandService.updateArticle(updateCommand);
        verify(articleRepository).updateArticle(any(Article.class));
    }

    @Test
    public void deleteArticle() {
        ArticleCommand articleCommand = new ArticleCommand.ArticleCommandBuilder()
                .id(1L)
                .title("wet cappuccino recipe")
                .body("wet cappuccino recipe:")
                .userId(1L)
                .tags(new HashSet<>(Arrays.asList(
                        new Tag(1L, "Recipe"),
                        new Tag(2L, "Cappuccino"),
                        new Tag(3L, "Wet Cappuccino")
                )))
                .build();
        Article article = ArticleAssembler.createArticle(articleCommand);
        ArticleCommand deleteCommand = new ArticleCommand.ArticleCommandBuilder().id(1L).build();

        given(articleRepository.findArticleById(1L)).willReturn(article);

        articleCommandService.deleteArticle(deleteCommand);

        verify(articleRepository).removeArticle(any(Article.class));
    }

}