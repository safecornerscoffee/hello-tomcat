package com.safecornerscoffee.dto;

import com.safecornerscoffee.domain.Tag;

import java.util.Set;

public class ArticleDTO {
    private Long id;
    private String title;
    private String body;
    private Long userId;
    private Set<Tag> tags;

    public ArticleDTO(Long id, String title, String body, Long userId, Set<Tag> tags) {
        this.id = id;
        this.title = title;
        this.body = body;
        this.userId = userId;
        this.tags = tags;
    }

    public ArticleDTO() {

    }

    public Long getUserId() {
        return userId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Set<Tag> getTags() {
        return tags;
    }

    public void setTags(Set<Tag> tags) {
        this.tags = tags;
    }

    public static class ArticleDTOBuilder {
        private Long id;
        private String title;
        private String body;
        private Long userId;
        private Set<Tag> tags;

        public ArticleDTOBuilder() {
            this.id = 0L;
            this.title = "";
            this.body = "";
            this.userId = 0L;
        }

        public ArticleDTOBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public ArticleDTOBuilder title(String title) {
            this.title = title;
            return this;
        }

        public ArticleDTOBuilder body(String body) {
            this.body = body;
            return this;
        }

        public ArticleDTOBuilder userId(Long userId) {
            this.userId = userId;
            return this;
        }

        public ArticleDTOBuilder tags(Set<Tag> tags) {
            this.tags = tags;
            return this;
        }

        public ArticleDTO build() {
            return new ArticleDTO(id, title, body, userId, tags);
        }
    }
}
