package com.safecornerscoffee.medium.assembler;

import com.safecornerscoffee.medium.domain.Profile;
import com.safecornerscoffee.medium.domain.User;
import com.safecornerscoffee.medium.dto.UserDTO;

public class UserAssembler {
    public static UserDTO writeDTO(User user) {
        UserDTO dto = new UserDTO();
        if (user.getId() != null) dto.setId(user.getId());
        if (user.getUsername() != null) dto.setUsername(user.getUsername());
        if (user.getEmail() != null) dto.setEmail(user.getEmail());
        if (user.getPassword() != null) dto.setPassword(user.getPassword());
        if (user.getProfile() != null && user.getProfile().getName() != null)
            dto.setProfileName(user.getProfile().getName());
        if (user.getProfile() != null && user.getProfile().getImage() != null)
            dto.setProfileImage(user.getProfile().getImage());
        return dto;
    }

    public static User createUser(UserDTO dto) {
        Long id = dto.getId();
        String username = dto.getUsername();
        String email = dto.getEmail();
        String password = dto.getPassword();
        String name = dto.getProfileName();
        String image = dto.getProfileImage();
        Profile profile = new Profile(name, image);

        return new User.UserBuilder(id, username, email, password).profile(profile).build();
    }
}
