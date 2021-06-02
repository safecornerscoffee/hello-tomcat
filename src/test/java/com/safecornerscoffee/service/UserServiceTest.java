package com.safecornerscoffee.service;

import com.safecornerscoffee.mapper.UserMapper;
import com.safecornerscoffee.domain.User;
import com.safecornerscoffee.service.dto.UserDTO;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/web/WEB-INF/applicationContext.xml")

public class UserServiceTest {

    @Autowired
    UserMapper userMapper;

    @Autowired
    UserService userService;

    UserDTO userDTO;
    User user;

    String username = "coffee";
    String email = "coffe@safecornerscoffee.com";
    String name = "coffee";
    String password = "coffee";

    @Before
    public void beforeEach() {
        userDTO = userService.signUp(username, email, name, password);
        user = userMapper.selectUserById(userDTO.getId());
    }

    @After
    public void afterEach() {
        userMapper.deleteUser(user);
    }

    @Test
    public void signUpTest() {

        assertEquals(username, userDTO.getUsername());
        assertEquals(email, userDTO.getEmail());
        assertEquals(name, userDTO.getName());
    }

    @Test
    public void HashingPasswordWithBcryptWhenSignUpTest() {

        assertTrue(BCrypt.checkpw(password, user.getPassword()));
   }

   @Test
   public void signInTest() {
        UserDTO signedUserDTO = userService.signIn(email, password);

        assertEquals(email, signedUserDTO.getEmail());
        assertEquals(name, signedUserDTO.getName());
        assertTrue(BCrypt.checkpw(password, signedUserDTO.getPassword()));
   }

   @Test(expected = IllegalStateException.class)
   public void ThrowErrorWhenSignInWithInvalidUserTest() {
       String invalidPassword = "invalid-coffee";
       userService.signUp(username, email, name, password);

       try {
           userService.signIn(email, invalidPassword);
       } catch (Exception e) {
           assertEquals(IllegalStateException.class, e.getClass());
           assertEquals(e.getMessage(), "invalid email or password");
       }
   }

   @Test
   public void FindExistUserTest() {
       UserDTO findUserDTO = userService.getUser(userDTO.getId());
       User findUser = userMapper.selectUserById(userDTO.getId());
       assertEquals(findUserDTO.getId(), findUser.getId());
   }

   @Test
    public void FindNoneExistUserTest() {
        Long invalidUserId = -9999L;
        try {
            userService.getUser(invalidUserId);
        } catch (Exception e) {
            assertEquals(IllegalStateException.class, e.getClass());
            assertEquals(e.getMessage(), "invalid email or password");
        }
   }

}