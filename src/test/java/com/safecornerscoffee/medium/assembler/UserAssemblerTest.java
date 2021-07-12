package com.safecornerscoffee.medium.assembler;

import com.safecornerscoffee.medium.domain.Profile;
import com.safecornerscoffee.medium.domain.User;
import com.safecornerscoffee.medium.dto.UserDTO;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;


public class UserAssemblerTest {

    Long id = 1L;
    String username = "coffee";
    String email = "coffe@safecornerscoffee.com";
    String password = "coffee";
    String name = "coffee";
    String image = "coffee.png";

    @Test
    public void writeDTO() {
        User user = new User.UserBuilder(id, username, email, password).profile(new Profile(name, image)).build();

        UserDTO dto = UserAssembler.writeDTO(user);

        assertThat(dto).isNotNull();
        assertThat(dto.getId()).isEqualTo(id);
        assertThat(dto.getUsername()).isEqualTo(username);
        assertThat(dto.getEmail()).isEqualTo(email);
        assertThat(dto.getPassword()).isEqualTo(password);
        assertThat(dto.getProfileName()).isEqualTo(name);
        assertThat(dto.getProfileImage()).isEqualTo(image);
    }

    @Test
    public void createUserFromDTO() {
        UserDTO dto = new UserDTO.UserDTOBuilder().id(id).username(username).email(email).password(password).profileName(name).profileImage(image).build();

        User user = UserAssembler.createUser(dto);

        assertThat(user).isNotNull();
        assertThat(user.getId()).isEqualTo(id);
        assertThat(user.getUsername()).isEqualTo(username);
        assertThat(user.getEmail()).isEqualTo(email);
        assertThat(user.getPassword()).isEqualTo(password);
        assertThat(user.getProfile()).isEqualTo(new Profile(name, image));
    }
}