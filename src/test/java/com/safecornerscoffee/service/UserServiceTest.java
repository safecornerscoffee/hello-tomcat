package com.safecornerscoffee.service;

import com.safecornerscoffee.dao.UserDao;
import com.safecornerscoffee.domain.User;
import com.safecornerscoffee.service.dto.UserDTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/web/WEB-INF/applicationContext.xml")
@Transactional
public class UserServiceTest {

    @Autowired
    UserDao userDao;

    @Autowired
    UserService userService;

    @Test
    public void signUpTest() {
        String email = "coffe@safecornerscoffee.com";
        String name = "coffee";
        String password = "coffee";
        UserDTO userDTO = userService.signUp(email, name, password);

        assertEquals(email, userDTO.getEmail());
        assertEquals(name, userDTO.getName());
        assertEquals(password, userDTO.getPassword());

    }
}