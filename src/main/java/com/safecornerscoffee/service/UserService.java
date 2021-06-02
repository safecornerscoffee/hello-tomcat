package com.safecornerscoffee.service;

import com.safecornerscoffee.assembler.UserAssembler;
import com.safecornerscoffee.mapper.UserMapper;
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

    private final UserMapper userMapper;

    public UserService(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Transactional
    public UserDTO signUp(UserDTO userDTO) {
        if (isExistUsername(userDTO.getUsername())) {
            throw new IllegalStateException("already exists" + userDTO.getUsername());
        }
        if (isExistEmailAddress(userDTO.getEmail())) {
            throw new IllegalStateException("already exists" + userDTO.getEmail());
        }

        userDTO.setId(userMapper.nextId());
        User user = UserAssembler.createUser(userDTO);

        String hashedPassword = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
        user.setPassword(hashedPassword);

        userMapper.insertUser(user);

        return UserAssembler.writeDTO(user);
    }


    public UserDTO signIn(UserDTO userDTO) {
        String username = userDTO.getUsername();
        String password = userDTO.getPassword();

        User user = userMapper.selectUserByUsername(username);
        if (user == null) {
            throw new IllegalStateException("invalid email or password");
        }

        String hashedPassword = user.getPassword();
        if(!BCrypt.checkpw(password, hashedPassword)) {
            throw new IllegalStateException("invalid email or password");
        }

        String token = generateJWT(user.getUsername());

        UserDTO userWithToken =  UserAssembler.writeDTO(user);
        userWithToken.setToken(token);

        return userWithToken;
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

    public UserDTO getUser(Long id) {
        User user = userMapper.selectUserById(id);
        if (user == null) {
            throw new IllegalStateException("invalid email or password");
        }
        return UserAssembler.writeDTO(user);
    }

    public List<UserDTO> getAllUsers() {
        List<User> users = userMapper.selectAllUsers();
        if (users == null || users.isEmpty()) {
            return Collections.emptyList();
        }

        return users.stream()
                .map(UserAssembler::writeDTO)
                .collect(Collectors.toList());
    }

    public void dropUser(UserDTO userDTO) {
        User user = userMapper.selectUserById(userDTO.getId());
        userMapper.deleteUser(user);
    }
}
