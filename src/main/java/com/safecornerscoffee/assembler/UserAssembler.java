package com.safecornerscoffee.assembler;

import com.safecornerscoffee.domain.Profile;
import com.safecornerscoffee.domain.User;
import com.safecornerscoffee.dto.UserDTO;

public class UserAssembler {
    public static UserDTO writeDTO(User user) {
        UserDTO userDto = new UserDTO();
        userDto.setId(user.getId());
        userDto.setUsername(user.getUsername());
        userDto.setEmail(user.getEmail());
        userDto.setPassword(user.getPassword());
        userDto.setName(user.getProfile().getName());
        return userDto;
    }

    public static User createUser(UserDTO userDTO) {
        return new User(
                userDTO.getId(),
                userDTO.getUsername(),
                userDTO.getEmail(),
                userDTO.getPassword(),
                new Profile(userDTO.getName(), userDTO.getName() + "png")
        );
    }
}
