package com.safecornerscoffee.util;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.crypto.bcrypt.BCrypt;

import java.security.Key;

public class AuthHelper {

    public static String generateFromPassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

    public static boolean compareHashAndPassword(String hashedPassword, String password) {
        return BCrypt.checkpw(password, hashedPassword);
    }

    public static String generateToken(String username) {
        Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);

        return Jwts.builder().setSubject(username).signWith(key).compact();
    }

    public static String usernameFromToken(String token) {
        return token;
    }
}
