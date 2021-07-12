package com.safecornerscoffee.medium.dto;

public class UserDTO {
    Long id;
    String username;
    String email;
    String password;
    String profileName;
    String profileImage;
    String token;

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", profileName='" + profileName + '\'' +
                ", profileImage='" + profileImage + '\'' +
                ", token='" + token + '\'' +
                '}';
    }

    public static class UserDTOBuilder {
        Long id;
        String username;
        String email;
        String password;
        String profileName;
        String profileImage;
        String token;

        public UserDTOBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public UserDTOBuilder username(String username) {
            this.username = username;
            return this;
        }

        public UserDTOBuilder email(String email) {
            this.email = email;
            return this;
        }

        public UserDTOBuilder password(String password) {
            this.password = password;
            return this;
        }

        public UserDTOBuilder profileName(String profileName) {
            this.profileName = profileName;
            return this;
        }

        public UserDTOBuilder profileImage(String profileImage) {
            this.profileImage = profileImage;
            return this;
        }

        public UserDTOBuilder token(String token) {
            this.token = token;
            return this;

        }

        public UserDTO build() {
            UserDTO userDTO = new UserDTO();
            userDTO.setId(id);
            userDTO.setUsername(username);
            userDTO.setEmail(email);
            userDTO.setPassword(password);
            userDTO.setProfileName(profileName);
            userDTO.setProfileImage(profileImage);
            userDTO.setToken(token);

            return userDTO;
        }
    }
}
