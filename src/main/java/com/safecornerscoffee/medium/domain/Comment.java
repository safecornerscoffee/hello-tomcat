package com.safecornerscoffee.medium.domain;

import java.util.UUID;

public class Comment {
    private String id;
    private String body;
    private Long articleId;
    private Long userId;

    public Comment() {

    }

    public Comment(Long articleId, Long userId, String body) {
        this.id = UUID.randomUUID().toString();
        this.body = body;
        this.articleId = articleId;
        this.userId = userId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Long getArticleId() {
        return articleId;
    }

    public void setArticleId(Long articleId) {
        this.articleId = articleId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
