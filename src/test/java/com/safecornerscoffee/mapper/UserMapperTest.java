package com.safecornerscoffee.mapper;

import com.safecornerscoffee.domain.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.*;

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
        String name = "coffee";
        String password = "coffee";
        user = new User(id, username, email, name, password);
        userMapper.insertUser(user);
    }
    @After
    public void afterEach() {
        userMapper.deleteUser(user);
    }

    @Test
    public void insertUserTest() {
        Long id = userMapper.nextId();
        String username = "latte";
        String email = "latte@safecornerscoffee.com";
        String name = "latte";
        String password = "latte";
        User insertUser = new User(id, username, email, name, password);

        userMapper.insertUser(insertUser);
        userMapper.deleteUser(insertUser);
    }

    @Test
    public void selectUserByIdTest() {
        User returnedUser =  userMapper.selectUserById(user.getId());
        assertEquals((long) user.getId(), (long) returnedUser.getId());
        assertEquals(user.getName(), returnedUser.getName());
    }

    @Test
    public void selectUserByUsernameTest() {
        User returnedUser = userMapper.selectUserByUsername(user.getUsername());
        assertEquals((long) user.getId(), (long) returnedUser.getId());
    }

    @Test
    public void selectUserByEmailTest() {

        User returnedUser =  userMapper.selectUserById(user.getId());

        assertEquals((long) user.getId(), (long) returnedUser.getId());
        assertEquals(user.getName(), returnedUser.getName());
    }

    @Test
    public void retuningNullWhenSelectUserNotExistsTest() {
        String invalidEmailAddress= "invalid@invalid.com";
        User returnedUser = userMapper.selectUserByEmail(invalidEmailAddress);
        assertNull(returnedUser);
    }

}