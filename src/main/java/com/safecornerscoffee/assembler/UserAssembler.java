package com.safecornerscoffee.assembler;

import com.safecornerscoffee.domain.User;
import com.safecornerscoffee.service.dto.UserDTO;

public class UserAssembler {
    public static UserDTO writeDTO(User user) {
        UserDTO userDto = new UserDTO();
        userDto.setId(user.getId());
        userDto.setUsername(user.getUsername());
        userDto.setEmail(user.getEmail());
        userDto.setName(user.getName());
        userDto.setPassword(user.getPassword());
        return userDto;
    }

    public static User createUser(UserDTO userDTO) {
        return new User(
                userDTO.getId(),
                userDTO.getUsername(),
                userDTO.getEmail(),
                userDTO.getName(),
                userDTO.getPassword()
        );
    }
}
