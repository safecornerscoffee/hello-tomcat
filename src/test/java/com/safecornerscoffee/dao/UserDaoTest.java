package com.safecornerscoffee.dao;

import com.safecornerscoffee.domain.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/web/WEB-INF/applicationContext.xml")
@Transactional
public class UserDaoTest {

    @Autowired
    private UserDao userDao;

    @Test
    public void insertUserTest() {
        User user = new User();
        user.setEmail("coffee@safecornerscoffee.com");
        user.setName("coffee");
        user.setPassword("coffee");
        Long returnedId;
        returnedId = userDao.insertUser(user);
        assertNotNull(returnedId);
    }

    @Test
    public void selectUserByIdTest() {
        User user = new User();
        user.setEmail("latte@safecornerscoffee.com");
        user.setName("latte");
        user.setPassword("latte");

        Long returnedId = userDao.insertUser(user);
        User returnedUser =  userDao.selectUserById(returnedId);

        assertEquals((long) returnedId, (long) returnedUser.getId());
        assertEquals(user.getName(), returnedUser.getName());
    }


    @Test
    public void selectUserByEmailAddressTest() {
        User user = new User();
        user.setEmail("latte@safecornerscoffee.com");
        user.setName("latte");
        user.setPassword("latte");

        Long returnedId = userDao.insertUser(user);
        User returnedUser =  userDao.selectUserById(returnedId);

        assertEquals((long) returnedId, (long) returnedUser.getId());
        assertEquals(user.getName(), returnedUser.getName());
    }

    @Test
    public void retuningNullWhenSelectUserByInvalidEmailAddressTest() {
        String invalidEmailAddress= "invalid@invalid.com";
        User returnedUser = userDao.selectUserByEmail(invalidEmailAddress);
        assertNull(returnedUser);
    }

    public void updateUserTest() {
        User user = new User();
        user.setName("update");
        user.setPassword("update");
        Long returnedId = userDao.insertUser(user);
        user.setId(returnedId);

        user.setName("updated");
        user.setPassword("updated");
        userDao.updateUser(user);

        User updatedUser = userDao.selectUserById(user.getId());

        assertEquals("updated", updatedUser.getName());
        assertEquals("updated", updatedUser.getPassword());

        assertEquals(user.getId(), updatedUser.getId());
        assertNotEquals(user.getName(), updatedUser.getName());
        assertNotEquals(user.getPassword(), updatedUser.getPassword());
    }

    @Test
    public void deleteUser() {
        User user = new User();
        user.setEmail("delete@safecornerscoffe.com");
        user.setName("delete");
        user.setPassword("delete");

        Long returnedId;
        returnedId = userDao.insertUser(user);
        user.setId(returnedId);

        userDao.deleteUser(user);

        User deletedUser = userDao.selectUserById(returnedId);

        assertNull(deletedUser);
    }
}