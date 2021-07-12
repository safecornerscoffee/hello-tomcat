package com.safecornerscoffee.integration.factory;

import com.safecornerscoffee.medium.domain.Tag;
import com.safecornerscoffee.medium.factory.TagFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/web/WEB-INF/applicationContext.xml")
public class TagFactoryTest {

    @Autowired
    TagFactory tagFactory;

    @Test
    public void tagDoesNotDuplicatedTest() {
        Tag one = tagFactory.forName("Rider");
        Tag other = tagFactory.forName("Rider");

        assertThat(one.getId()).isEqualTo(other.getId());
        assertThat(one.getName()).isEqualTo(other.getName());
    }
}