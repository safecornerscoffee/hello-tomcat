package com.safecornerscoffee.service;

import com.safecornerscoffee.domain.User;
import com.safecornerscoffee.dto.UserDTO;
import com.safecornerscoffee.exception.InvalidUsernameOrPassword;
import com.safecornerscoffee.exception.NotFoundUserException;
import com.safecornerscoffee.mapper.UserMapper;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/web/WEB-INF/applicationContext.xml")
public class UserServiceTest {

    @Autowired
    UserMapper userMapper;

    @Autowired
    UserService userService;

    UserDTO signUpRequest;
    User user;

    String username = "coffee";
    String email = "coffe@safecornerscoffee.com";
    String password = "coffee";
    String name = "coffee";
    String image = "coffee.png";

    @Before
    public void beforeEach() {
        signUpRequest = new UserDTO.UserDTOBuilder().username(username).email(email).password(password)
                .profileName(name).profileImage(image).build();
        user = userService.signUp(signUpRequest);
    }

    @After
    public void afterEach() {
        userMapper.deleteUser(user);
    }

    @Test
    public void signUpTest() {
        assertThat(user.getUsername()).isEqualTo(username);
        assertThat(user.getEmail()).isEqualTo(email);
        assertThat(BCrypt.checkpw(password, user.getPassword())).isTrue();
    }

    @Test
    public void HashingPasswordWithBcryptWhenSignUpTest() {
        assertThat(BCrypt.checkpw(password, user.getPassword())).isTrue();
   }

   @Test
   public void signInTest() {
       UserDTO signInRequest = new UserDTO.UserDTOBuilder().username(username).password(password).build();
       User response = userService.signIn(signInRequest);

       assertThat(response.getId()).isEqualTo(user.getId());
       assertThat(response.getUsername()).isEqualTo(username);
       assertThat(response.getEmail()).isEqualTo(email);
       assertThat(BCrypt.checkpw(password, response.getPassword())).isTrue();
   }

    @Test()
    public void signInWithInvalidUsername() {
        String invalidUsername = "invalid-coffee";
        UserDTO signInRequest = new UserDTO.UserDTOBuilder().username(invalidUsername).password(password).build();

        assertThatThrownBy(() -> {
            userService.signIn(signInRequest);
        }).isInstanceOf(InvalidUsernameOrPassword.class).hasMessageContaining("invalid username or password.");
    }

    @Test()
    public void signInWithInvalidPassword() {
        String invalidPassword = "invalid-coffee";
        UserDTO signInRequest = new UserDTO.UserDTOBuilder().username(username).password(invalidPassword).build();

        assertThatThrownBy(() -> {
            userService.signIn(signInRequest);
        }).isInstanceOf(InvalidUsernameOrPassword.class).hasMessageContaining("invalid username or password.");
    }

    @Test()
    public void signInWithNoneExistUsername() {
        String noneExistUsername = "none-exist-coffee";
        UserDTO signInRequest = new UserDTO.UserDTOBuilder().username(noneExistUsername).password(password).build();

        assertThatThrownBy(() -> {
            userService.signIn(signInRequest);
        }).isInstanceOf(InvalidUsernameOrPassword.class).hasMessageContaining("invalid username or password.");
    }


    @Test
    public void FindExistUserTest() {
        // todo why do i this test?
        User result = userMapper.selectUserById(user.getId());
        assertThat(result.getId()).isEqualTo(user.getId());
    }

    @Test
    public void NotFoundUserException() {
        Long noneExistUserId = 0L;
        assertThatThrownBy(() -> {
            userService.getUser(noneExistUserId);
        }).isInstanceOf(NotFoundUserException.class).hasMessageContaining("NotFoundUserException");
    }
}