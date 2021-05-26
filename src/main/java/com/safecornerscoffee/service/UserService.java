package com.safecornerscoffee.service;

import com.safecornerscoffee.dao.UserDao;
import com.safecornerscoffee.domain.User;
import com.safecornerscoffee.service.dto.UserDTO;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import java.security.Key;

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

        String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());
        user.setPassword(hashedPassword);

        Long returnedId = userDao.insertUser(user);
        user.setId(returnedId);


        return UserDTO.fromUser(user);
    }

    public UserDTO signIn(String email, String candidatePassword) {

        User user = userDao.selectUserByEmail(email);
        if (user == null) {
            throw new IllegalStateException("invalid email or password");
        }
        String hashedPassword = user.getPassword();
        if(!BCrypt.checkpw(candidatePassword, hashedPassword)) {
            throw new IllegalStateException("invalid email or password");
        }

        String token = generateJWT(user.getEmail());
        UserDTO userDTO = UserDTO.fromUser(user);
        userDTO.setToken(token);
        return userDTO;
    }

    private String generateJWT(String email) {
        Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);

        String jws = Jwts.builder().setSubject(email).signWith(key).compact();

        return jws;
    }

    private boolean isExistEmailAddress(String email) {
        User existUser = userDao.selectUserByEmail(email);

        if (existUser == null) {
            return false;
        }
        return true;
    }

    public UserDTO getUser(Long id) {
        User user = userDao.selectUserById(id);
        if (user == null) {
            throw new IllegalStateException("invalid email or password");
        }
        return UserDTO.fromUser(user);
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
