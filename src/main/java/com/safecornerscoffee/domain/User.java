package com.safecornerscoffee.domain;

public class User {
    private Long id;
    private String username;
    private String email;
    private String password;
    private Profile profile;

    public User() {

    }

    public User(Long id, String username, String email, String password, Profile profile) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.profile = profile;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", name='" + profile + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    public static class UserBuilder {
        private final Long id;
        private final String username;
        private final String email;
        private final String password;
        private Profile profile;

        public UserBuilder(Long id, String username, String email, String password) {
            this.id = id;
            this.username = username;
            this.email = email;
            this.password = password;
        }

        public UserBuilder profile(Profile profile) {
            this.profile = profile;
            return this;
        }

        public User build() {
            return new User(id, username, email, password, profile);
        }
    }

}
