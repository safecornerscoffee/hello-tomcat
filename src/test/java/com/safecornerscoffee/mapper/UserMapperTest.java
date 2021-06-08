package com.safecornerscoffee.mapper;

import com.safecornerscoffee.domain.Profile;
import com.safecornerscoffee.domain.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/web/WEB-INF/applicationContext.xml")
public class UserMapperTest {

    @Autowired
    private UserMapper userMapper;

    User user;

    @Before
    public void beforeEach() {

        Long id = userMapper.nextId();
        String username = "coffee";
        String email = "coffee@safecornerscoffee.com";
        String password = "coffee";
        String name = "coffee";
        String image = "coffee.png";
        Profile profile = new Profile(name, image);
        user = new User(id, username, email, password, profile);
        userMapper.insertUser(user);
    }
    @After
    public void afterEach() {
        userMapper.deleteUser(user);
    }

    @Test
    @Transactional
    public void insertUserTest() {
        Long id = userMapper.nextId();
        String username = "mocha";
        String email = "mocha@safecornerscoffee.com";
        String password = "mocha";
        String name = "mocha";
        String imageUrl = "mocha.png";
        User insertUser = new User(id, username, email, password, new Profile(name, imageUrl));

        userMapper.insertUser(insertUser);
    }

    @Test
    public void selectUserByIdTest() {
        User result = userMapper.selectUserById(user.getId());

        assertThat(result.getId()).isEqualTo(user.getId());
        assertThat(result.getUsername()).isEqualTo(user.getUsername());
        assertThat(result.getEmail()).isEqualTo(user.getEmail());
    }

    @Test
    public void selectUserByUsernameTest() {
        User result = userMapper.selectUserByUsername(user.getUsername());

        assertThat(result.getId()).isEqualTo(user.getId());
        assertThat(result.getUsername()).isEqualTo(user.getUsername());
        assertThat(result.getEmail()).isEqualTo(user.getEmail());
    }

    @Test
    public void selectUserByEmailTest() {

        User result = userMapper.selectUserById(user.getId());

        assertThat(result.getId()).isEqualTo(user.getId());
        assertThat(result.getUsername()).isEqualTo(user.getUsername());
        assertThat(result.getEmail()).isEqualTo(user.getEmail());
    }

    @Test
    public void resultShouldBeNullWhenSelectNoneExistsUsername() {
        String NoneExistsUsername = "NoneExistsUsername";
        User result = userMapper.selectUserByUsername(NoneExistsUsername);

        assertThat(result).isNull();
    }

}