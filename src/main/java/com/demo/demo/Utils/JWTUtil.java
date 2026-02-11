package com.demo.demo.Utils;

import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Component
public class JWTUtil {

    private final SecretKey secretKey;
    private final long expirationTime;

    public JWTUtil(@Value("${jwt.secret}") String secreatKey, @Value("${jwt.expiration}") long expirationTime) {
        this.secretKey = Keys.hmacShaKeyFor(secreatKey.getBytes());
        this.expirationTime = expirationTime;
    }

    // Method to generate a JWT token for a given username
    public String generateToken(String userName) {

        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + expirationTime);

        return Jwts.builder()
                .subject(userName)
                .issuedAt(now)
                .expiration(expiryDate)
                .signWith(secretKey)
                .compact();
    }

    // Method to extract the username from a given JWT token
    public String extractSubject(String token) {
        return parseClaims(token).getSubject();
    }

    // Method to validate a given JWT token
    public boolean isTokenValid(String token) {
        try {
            parseClaims(token);
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    // Helper method to parse the claims from a JWT token
    private Claims parseClaims(String token) {
        return Jwts.parser()
                .verifyWith(secretKey)
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }
}
