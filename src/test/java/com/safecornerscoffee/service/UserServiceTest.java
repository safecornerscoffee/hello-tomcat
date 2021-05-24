package com.safecornerscoffee.service;

import com.safecornerscoffee.dao.UserDao;
import com.safecornerscoffee.domain.User;
import com.safecornerscoffee.service.dto.UserDTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
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
    }

    @Test
    public void HashingPasswordWithBcryptWhenSignUpTest() {
        String email = "coffe@safecornerscoffee.com";
        String name = "coffee";
        String password = "coffee";

        UserDTO userDTO = userService.signUp(email, name, password);

        User user = userDao.selectUserById(userDTO.getId());

        assertTrue(BCrypt.checkpw(password, user.getPassword()));
   }

   @Test
   @Transactional
   public void signInTest() {
       String email = "coffe@safecornerscoffee.com";
       String name = "coffee";
       String password = "coffee";

       UserDTO userDTO = userService.signUp(email, name, password);
       System.out.println(userDTO);

       UserDTO signedUserDTO = userService.signIn(email, password);
        System.out.println(signedUserDTO);

        assertEquals(email, signedUserDTO.getEmail());
        assertEquals(name, signedUserDTO.getName());
        assertTrue(BCrypt.checkpw(password, signedUserDTO.getPassword()));
   }

   @Test(expected = IllegalStateException.class)
   public void ThrowErrorWhenSignInWithInvalidUserTest() {
       String email = "coffe@safecornerscoffee.com";
       String name = "coffee";
       String password = "coffee";
       String invalidPassword = "invalid-coffee";
       userService.signUp(email, name, password);

       try {
           userService.signIn(email, invalidPassword);
       } catch (Exception e) {
           assertEquals(IllegalStateException.class, e.getClass());
           assertEquals(e.getMessage(), "invalid email or password");
       }

   }
}