package com.safecornerscoffee.service.dto;

import com.safecornerscoffee.domain.Article;
import com.safecornerscoffee.domain.Tag;

import java.util.List;

public class ArticleDTO {
    private Long id;
    private String title;
    private String body;
    private Long authorId;
    private List<Tag> tags;

    public static class Builder {
        private Long id;
        private String title;
        private String body;
        private Long authorId;
        private List<Tag> tags;

        public Builder() {
            this.id = 0L;
            this.title = "";
            this.body = "";
            this.authorId = 0L;
        }

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder title(String title) {
            this.title = title;
            return this;
        }
        public Builder body(String body) {
            this.body = body;
            return this;
        }
        public Builder authorId(Long authorId) {
            this.authorId = authorId;
            return this;
        }
        public Builder tags(List<Tag> tags) {
            this.tags = tags;
            return this;
        }
        public ArticleDTO build() {
            return new ArticleDTO(id, title, body, authorId, tags);
        }
    }

    public ArticleDTO() {

    }

    public ArticleDTO(Long id, String title, String body, Long authorId, List<Tag> tags) {
        this.id = id;
        this.title = title;
        this.body = body;
        this.authorId = authorId;
        this.tags = tags;
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

    public Long getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Long authorId) {
        this.authorId = authorId;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }
}
