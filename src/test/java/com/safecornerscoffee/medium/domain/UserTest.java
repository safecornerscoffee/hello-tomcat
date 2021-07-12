package com.safecornerscoffee.medium.domain;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class UserTest {


    @Test
    public void createUser() {
        Long id = 1L;
        String username = "coffee";
        String email = "coffe@safecornerscoffee.com";
        String password = "coffee";
        User user = new User.UserBuilder(id, username, email, password).build();

        assertThat(user.getId()).isNotNull();
        assertThat(user.getUsername()).isNotNull();
        assertThat(user.getEmail()).isNotNull();
        assertThat(user.getPassword()).isNotNull();
    }

}