package com.safecornerscoffee.medium.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@NoArgsConstructor
@Getter
@Setter
public class Comment {
    private String id;
    private String body;
    private Long articleId;
    private Long userId;

    public Comment(Long articleId, Long userId, String body) {
        this.id = UUID.randomUUID().toString();
        this.body = body;
        this.articleId = articleId;
        this.userId = userId;
    }
}
