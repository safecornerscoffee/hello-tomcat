package com.safecornerscoffee.dao;

import com.safecornerscoffee.domain.User;

import java.util.List;

public interface UserDao {
    public List<User> selectAllUsers();
    public User selectUserById(Long id);
    public User selectUserByEmail(String email);

    public Long insertUser(User user);
    public void updateUser(User user);
    public void deleteUser(User user);
}
