package com.safecornerscoffee.service;

import com.safecornerscoffee.domain.User;
import com.safecornerscoffee.dto.UserDTO;
import com.safecornerscoffee.exception.NotFoundUserException;
import com.safecornerscoffee.exception.UserAlreadyExistException;
import com.safecornerscoffee.mapper.UserMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.crypto.bcrypt.BCrypt;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.BDDMockito.*;


@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

    @InjectMocks
    UserService userService;

    @Mock
    UserMapper userMapper;

    UserDTO signUpRequest;
    User user;
    Long userId = 1L;
    String username = "coffee";
    String email = "coffe@safecornerscoffee.com";
    String password = "coffee";
    String name = "coffee";
    String image = "coffee.png";

    @Before
    public void beforeEach() {
        signUpRequest = new UserDTO.UserDTOBuilder().username(username).email(email).password(password)
                .profileName(name).profileImage(image).build();
        user = new User.UserBuilder(userId, username, email, BCrypt.hashpw(password, BCrypt.gensalt())).build();
    }

    @Test
    public void shouldSavedUser() {

        given(userMapper.selectUserByUsername(username)).willReturn(null);
        given(userMapper.selectUserByEmail(email)).willReturn(null);
        given(userMapper.nextId()).willReturn(userId);

        User savedUser = userService.signUp(signUpRequest);

        verify(userMapper).insertUser(any(User.class));
        assertThat(savedUser.getUsername()).isEqualTo(username);
        assertThat(savedUser.getEmail()).isEqualTo(email);
        assertThat(BCrypt.checkpw(password, savedUser.getPassword())).isTrue();
    }

    @Test
    public void shouldThrowExceptionWhenSavedUserWithExistingUsername() {
        given(userMapper.selectUserByUsername(username)).willReturn(user);
        assertThatThrownBy(() -> {
            User savedUser = userService.signUp(signUpRequest);
        }).isInstanceOf(UserAlreadyExistException.class);

        verify(userMapper).selectUserByUsername(username);
    }

    @Test
    public void shouldThrowExceptionWhenSavedUserWithExistingEmail() {
        given(userMapper.selectUserByUsername(username)).willReturn(null);
        given(userMapper.selectUserByEmail(email)).willReturn(user);
        assertThatThrownBy(() -> {
            User savedUser = userService.signUp(signUpRequest);
        }).isInstanceOf(UserAlreadyExistException.class);

        verify(userMapper).selectUserByEmail(email);
        verify(userMapper, never()).insertUser(any(User.class));
    }

    @Test
    public void signInTest() {
        UserDTO signInRequest = new UserDTO.UserDTOBuilder().username(username).password(password).build();

        given(userMapper.selectUserByUsername(username)).willReturn(user);

        User signInUser = userService.signIn(signInRequest);

        assertThat(signInUser.getId()).isEqualTo(userId);
        assertThat(signInUser.getUsername()).isEqualTo(username);
        assertThat(signInUser.getEmail()).isEqualTo(email);
        assertThat(BCrypt.checkpw(password, signInUser.getPassword())).isTrue();
    }

    @Test
    public void shouldThrownErrorWhenSignInWithInvalidPassword() {

    }

    @Test
    public void shouldDeletedUser() {
        UserDTO dropUserRequest = new UserDTO.UserDTOBuilder().id(1L).build();
        given(userMapper.selectUserById(userId)).willReturn(user);

        userService.dropUser(dropUserRequest);

        verify(userMapper).deleteUser(any(User.class));
    }

    @Test
    public void shouldThrownErrorWhenDeletingNoneExistingUser() {
        UserDTO dropUserRequest = new UserDTO.UserDTOBuilder().id(1L).build();
        given(userMapper.selectUserById(userId)).willReturn(null);

        assertThatThrownBy(() -> {
            userService.dropUser(dropUserRequest);
        }).isInstanceOf(NotFoundUserException.class);

        verify(userMapper, never()).deleteUser(any(User.class));

    }
}