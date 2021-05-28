package com.safecornerscoffee.service.dto;

import com.safecornerscoffee.domain.Article;

public class ArticleDTO {
    private Long id;
    private String title;
    private String body;
    private Long authorId;

    public static class Builder {
        private Long id;
        private String title;
        private String body;
        private Long authorId;

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
        public ArticleDTO build() {
            return new ArticleDTO(id, title, body, authorId);
        }
    }

    public ArticleDTO() {

    }

    public ArticleDTO(Long id, String title, String body, Long authorId) {
        this.id = id;
        this.title = title;
        this.body = body;
        this.authorId = authorId;
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

    public static ArticleDTO from(Article article) {
        return new ArticleDTO.Builder()
                .id(article.getId())
                .title(article.getTitle())
                .body(article.getBody())
                .authorId(article.getAuthorId())
                .build();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ArticleDTO that = (ArticleDTO) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (title != null ? !title.equals(that.title) : that.title != null) return false;
        if (body != null ? !body.equals(that.body) : that.body != null) return false;
        return authorId != null ? authorId.equals(that.authorId) : that.authorId == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (body != null ? body.hashCode() : 0);
        result = 31 * result + (authorId != null ? authorId.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "ArticleDTO{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", body='" + body + '\'' +
                ", authorId=" + authorId +
                '}';
    }
}
