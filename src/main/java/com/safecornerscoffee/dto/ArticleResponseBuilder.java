package com.safecornerscoffee.dto;

import com.safecornerscoffee.domain.Article;
import com.safecornerscoffee.domain.Tag;
import com.safecornerscoffee.domain.User;

import java.util.Set;

public class ArticleResponseBuilder {

    private Long id;
    private String title;
    private String body;
    private Set<Tag> tags;

    private Long userId;
    private String username;
    private String profileName;
    private String profileImage;

    private ArticleResponseBuilder(Article article, User user) {
        this.id = article.getId();
        this.title = article.getTitle();
        this.body = article.getBody();
        this.tags = article.getTags();
        this.userId = user.getId();
        this.username = user.getUsername();
        this.profileName = user.getProfile().getName();
        this.profileImage = user.getProfile().getImage();
    }

    public static ArticleResponseBuilder ArticleResponseBuilder(Article article, User user) {
        if (!article.getUserId().equals(user.getId())) {
            throw new IllegalStateException("user id is not same");
        }
        return new ArticleResponseBuilder(article, user);
    }

    public ArticleResponse build() {
        return new ArticleResponse(id, title, body, tags, userId, username, profileName, profileImage);
    }

}
