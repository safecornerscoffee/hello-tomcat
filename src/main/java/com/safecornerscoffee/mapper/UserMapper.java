package com.safecornerscoffee.mapper;

import com.safecornerscoffee.domain.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {
    Long nextId();
    List<User> selectAllUsers();
    User selectUserById(Long id);
    User selectUserByUsername(String username);
    User selectUserByEmail(String email);

    void insertUser(User user);
    void updateUser(User user);
    void deleteUser(User user);
}
