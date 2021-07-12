package com.safecornerscoffee.medium.dto;

import com.safecornerscoffee.medium.domain.Tag;

import java.util.Set;

public class ArticleResponse {
    private Long id;
    private String title;
    private String body;
    private Set<Tag> tags;

    private Long userId;
    private String username;
    private String profileName;
    private String profileImage;

    public ArticleResponse() {

    }

    public ArticleResponse(Long id, String title, String body, Set<Tag> tags, Long userId, String username, String profileName, String profileImage) {
        this.id = id;
        this.title = title;
        this.body = body;
        this.tags = tags;
        this.userId = userId;
        this.username = username;
        this.profileName = profileName;
        this.profileImage = profileImage;
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

    public Set<Tag> getTags() {
        return tags;
    }

    public void setTags(Set<Tag> tags) {
        this.tags = tags;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getProfileName() {
        return profileName;
    }

    public void setProfileName(String profileName) {
        this.profileName = profileName;
    }

    public String getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(String profileImage) {
        this.profileImage = profileImage;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ArticleResponse that = (ArticleResponse) o;

        if (!id.equals(that.id)) return false;
        if (!title.equals(that.title)) return false;
        if (!body.equals(that.body)) return false;
        if (tags != null ? !tags.equals(that.tags) : that.tags != null) return false;
        if (!userId.equals(that.userId)) return false;
        if (!username.equals(that.username)) return false;
        if (profileName != null ? !profileName.equals(that.profileName) : that.profileName != null) return false;
        return profileImage != null ? profileImage.equals(that.profileImage) : that.profileImage == null;
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + title.hashCode();
        result = 31 * result + body.hashCode();
        result = 31 * result + (tags != null ? tags.hashCode() : 0);
        result = 31 * result + userId.hashCode();
        result = 31 * result + username.hashCode();
        result = 31 * result + (profileName != null ? profileName.hashCode() : 0);
        result = 31 * result + (profileImage != null ? profileImage.hashCode() : 0);
        return result;
    }
}
