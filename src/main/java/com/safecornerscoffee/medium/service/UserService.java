package com.safecornerscoffee.medium.service;

import com.safecornerscoffee.medium.assembler.UserAssembler;
import com.safecornerscoffee.medium.domain.User;
import com.safecornerscoffee.medium.dto.UserDTO;
import com.safecornerscoffee.medium.exception.InvalidUsernameOrPassword;
import com.safecornerscoffee.medium.exception.NotFoundUserException;
import com.safecornerscoffee.medium.exception.UserAlreadyExistException;
import com.safecornerscoffee.medium.mapper.UserMapper;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.Key;
import java.util.Collections;
import java.util.List;

@Service
public class UserService {

    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserMapper userMapper, PasswordEncoder passwordEncoder) {
        this.userMapper = userMapper;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public User signUp(UserDTO signUpRequest) {
        if (isExistUsername(signUpRequest.getUsername()) || isExistEmailAddress(signUpRequest.getEmail())) {
            throw new UserAlreadyExistException();
        }

        Long id = userMapper.nextId();
        signUpRequest.setId(id);
        User user = UserAssembler.createUser(signUpRequest);

        String hashedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(hashedPassword);

        userMapper.insertUser(user);

        return user;
    }


    public User signIn(UserDTO signInRequest) {
        String username = signInRequest.getUsername();
        String password = signInRequest.getPassword();

        User user = userMapper.selectUserByUsername(username);
        if (user == null) {
            throw new InvalidUsernameOrPassword();
        }

        String hashedPassword = user.getPassword();
        if (!BCrypt.checkpw(password, hashedPassword)) {
            throw new InvalidUsernameOrPassword();
        }

        return user;
    }

    private String generateJWT(String email) {
        Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);

        return Jwts.builder().setSubject(email).signWith(key).compact();
    }

    private boolean isExistUsername(String username) {
        User existUser = userMapper.selectUserByUsername(username);
        return existUser != null;
    }
    private boolean isExistEmailAddress(String email) {
        User existUser = userMapper.selectUserByEmail(email);

        return existUser != null;
    }

    public User getUser(Long id) {
        User user = userMapper.selectUserById(id);
        if (user == null) {
            throw new NotFoundUserException();
        }
        return user;
    }

    public List<User> getAllUsers() {
        List<User> users = userMapper.selectAllUsers();
        if (users == null || users.isEmpty()) {
            return Collections.emptyList();
        }

        return users;
    }

    public void dropUser(UserDTO dropUserRequest) {
        User user = userMapper.selectUserById(dropUserRequest.getId());
        if (user == null) {
            throw new NotFoundUserException();
        }
        userMapper.deleteUser(user);
    }

    public User findByUsername(String username) {
        return userMapper.selectUserByUsername(username);
    }
}
