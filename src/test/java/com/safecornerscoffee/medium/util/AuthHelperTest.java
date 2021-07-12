package com.safecornerscoffee.medium.util;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class AuthHelperTest {

    @Test
    public void compareHashAndPassword() {
        String password = "cappuccino";

        String hashedPassword = AuthHelper.generateFromPassword(password);

        assertThat(AuthHelper.compareHashAndPassword(hashedPassword, password)).isTrue();
    }

    @Test
    public void generateToken() {

    }

}