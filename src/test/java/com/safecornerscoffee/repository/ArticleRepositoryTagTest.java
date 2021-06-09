package com.safecornerscoffee.repository;

import com.safecornerscoffee.domain.Tag;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/web/WEB-INF/applicationContext.xml")
public class ArticleRepositoryTagTest {

    @Autowired
    ArticleRepository articleRepository;

    @Test
    @Transactional
    public void getTagByNoneExistNameTest() {
        String tagName = "Vanilla";
        Tag tag = articleRepository.getTagByName(tagName);

        assertThat(tag.getId()).isNotNull();
        assertThat(tag.getName()).isEqualTo(tagName);

    }

    @Test
    @Transactional
    public void getTagByExistTagNameTest() {
        String tagName = "Cinnamon";
        Tag tag = articleRepository.getTagByName(tagName);

        Tag savedTag = articleRepository.getTagByName(tagName);

        assertThat(savedTag.getId()).isEqualTo(tag.getId());
        assertThat(savedTag.getName()).isEqualTo(tag.getName());

    }
}
