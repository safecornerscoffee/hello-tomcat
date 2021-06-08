package com.safecornerscoffee.assembler;

import com.safecornerscoffee.domain.Profile;
import com.safecornerscoffee.domain.User;
import com.safecornerscoffee.dto.UserDTO;

public class UserAssembler {
    public static UserDTO writeDTO(User user) {
        UserDTO dto = new UserDTO();
        dto.setId(user.getId());
        dto.setUsername(user.getUsername());
        dto.setEmail(user.getEmail());
        dto.setPassword(user.getPassword());
        dto.setProfileName(user.getProfile().getName());
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
