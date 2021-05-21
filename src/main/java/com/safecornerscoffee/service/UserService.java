package com.safecornerscoffee.service;

import com.safecornerscoffee.dao.UserDao;
import com.safecornerscoffee.domain.User;
import com.safecornerscoffee.service.dto.UserDTO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserDao userDao;

    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    @Transactional
    public UserDTO signUp(String email, String name, String password) {

        if(isExistEmailAddress(email)) {
            throw new IllegalStateException("already exists");
        }

        User user = new User();
        user.setEmail(email);
        user.setName(name);
        user.setPassword(password);

        Long returnedId = userDao.insertUser(user);
        user.setId(returnedId);

        return UserDTO.fromUser(user);
    }

    private boolean isExistEmailAddress(String email) {
        User existUser = userDao.selectUserByEmail(email);

        if (existUser == null) {
            return false;
        }
        System.out.println("existUser: " + existUser.getId() + " " + existUser.getEmail() + " "
            + existUser.getName() + " " + existUser.getPassword());

        return true;

    }

    public List<UserDTO> getAllUsers() {
        List<User> users = userDao.selectAllUsers();
        if (users == null || users.isEmpty()) {
            return Collections.emptyList();
        }

        return users.stream()
                .map(UserDTO::fromUser)
                .collect(Collectors.toList());
    }
}
