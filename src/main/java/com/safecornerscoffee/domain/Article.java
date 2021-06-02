package com.safecornerscoffee.domain;

import java.util.List;

public class Article {
    private Long id;
    private String title;
    private String body;
    private Long authorId;
    private List<Tag> tags;

    public Article() {

    }

    public Article(Long id, String title, String body, Long authorId) {
        this.id = id;
        this.title = title;
        this.body = body;
        this.authorId = authorId;
    }

    public Article(Long id, String title, String body, Long authorId, List<Tag> tags) {
        this.id = id;
        this.title = title;
        this.body = body;
        this.authorId = authorId;
        this.tags = tags;
    }

    public void updateTitle(String title) {
        this.title = title;
    }

    public void updateBody(String body) {
        this.body = body;
    }

    public void addTag(Tag tag) {
        if (tags.contains(tag)) {
            throw new IllegalStateException("already have tag: " + tag.getName());
        }
        tags.add(tag);
    }
    public void removeTag(Tag tag) {
        if (!tags.contains(tag)) {
           throw new IllegalStateException("does not have tag: " + tag.getName());
        }
        tags.remove(tag);
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
