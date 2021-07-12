package com.safecornerscoffee.medium.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class User {
    private Long id;
    private String username;
    private String email;
    private String password;
    private Profile profile;

    public User(Long id, String username, String email, String password, Profile profile) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
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
        private Long id;
        private String username;
        private String email;
        private String password;
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
