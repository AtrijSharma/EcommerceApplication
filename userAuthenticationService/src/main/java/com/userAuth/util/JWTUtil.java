package com.userAuth.util;


import com.userAuth.entity.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class JWTUtil {

    private static final String SECRET_KEY = "3d8f2c4a3d9a3d2c4a3d9a3d8f2c4a3d9a3d2c4a3d9a3d8f2c4a3d9a3d2c4a3d";

    public static String generateToken(User user) {
        return Jwts.builder()
                .setSubject(user.getUsername())
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
    }
}

